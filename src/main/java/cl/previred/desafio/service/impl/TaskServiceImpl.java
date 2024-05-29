package cl.previred.desafio.service.impl;

import cl.previred.desafio.entity.TaskEntity;
import cl.previred.desafio.enums.StateTaskEnum;
import cl.previred.desafio.repository.TaskRepository;
import cl.previred.desafio.service.StateTaskService;
import cl.previred.desafio.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final StateTaskService stateTaskService;

    @Override
    public List<TaskEntity> findAll() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public TaskEntity createTask(TaskEntity task) {
        task.setStatus(stateTaskService.findByName(StateTaskEnum.CREATED));
        return taskRepository.save(task);
    }

    @Override
    public TaskEntity findById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    @Override
    public TaskEntity update(UUID id, String description) {
        TaskEntity update = this.findById(id);
        update.setDescription(description);
        return taskRepository.save(update);
    }

    @Override
    public TaskEntity delete(UUID id) {
        TaskEntity del = this.findById(id);
        taskRepository.delete(del);
        return del;
    }

    @Override
    public TaskEntity updateTaskStatus(UUID id, String status) {
        TaskEntity update = this.findById(id);
        update.setStatus(stateTaskService.findByName(StateTaskEnum.valueOf(status)));
        return taskRepository.save(update);
    }
}
