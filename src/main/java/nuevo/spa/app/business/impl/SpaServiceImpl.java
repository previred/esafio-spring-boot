package nuevo.spa.app.business.impl;

import com.spring.nuevo.spa.app.model.TaskListResponse;
import com.spring.nuevo.spa.app.model.TaskRequest;
import com.spring.nuevo.spa.app.model.TaskResponse;
import lombok.RequiredArgsConstructor;
import nuevo.spa.app.business.SpaService;
import nuevo.spa.app.entity.Task;
import nuevo.spa.app.entity.TaskStatus;
import nuevo.spa.app.processor.TaskProcessor;
import nuevo.spa.app.repository.TaskRepository;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static nuevo.spa.app.processor.TaskProcessor.toTask;
import static nuevo.spa.app.processor.TaskProcessor.toTaskResponse;

@Service
@RequiredArgsConstructor
public class SpaServiceImpl implements SpaService {

    private final TaskRepository repository;

    @Override
    public List<TaskListResponse> getTask() {
        return repository.findAll().stream().map(TaskProcessor::toTaskListResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse createTask(TaskRequest request) {
        return toTaskResponse(repository.save(toTask(request)));
    }

    @Override
    public TaskResponse updateTask(String id, TaskRequest request) {
        return repository.findById(Long.parseLong(id))
                .map(task -> {
                    Task entity = new Task();
                    TaskStatus taskStatus = new TaskStatus();
                    entity.setId(Long.parseLong(id));
                    entity.setTitle(request.getTitle());
                    entity.setDetail(request.getDetail());
                    taskStatus.setId(Long.valueOf(request.getStatus()));
                    entity.setStatus(taskStatus);
                    return toTaskResponse(repository.save(entity));
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteTask(String id) {
        repository.deleteById(Long.parseLong(id));
    }
}
