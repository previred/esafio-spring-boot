package cl.corellana.taskmanager.service.impl;

import cl.corellana.taskmanager.api.model.CreateTaskRequest;
import cl.corellana.taskmanager.api.model.TaskDto;
import cl.corellana.taskmanager.api.model.TaskStatus;
import cl.corellana.taskmanager.persistence.entities.TaskEntity;
import cl.corellana.taskmanager.persistence.entities.TaskMapper;
import cl.corellana.taskmanager.persistence.entities.TaskStatusEntity;
import cl.corellana.taskmanager.persistence.repositories.TaskRepository;
import cl.corellana.taskmanager.service.CreateTaskService;
import cl.corellana.taskmanager.service.DeleteTaskService;
import cl.corellana.taskmanager.service.GetTaskService;
import cl.corellana.taskmanager.service.UpdateTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements CreateTaskService, DeleteTaskService, GetTaskService, UpdateTaskService {

    private final TaskRepository taskRepository;
    @Override
    public void create(CreateTaskRequest request) {
        TaskEntity task = TaskEntity.builder()
                .title(request.getTitle())
                .status(TaskStatusEntity.of(TaskStatus.TO_DO))
                .build();
        taskRepository.save(task);
    }

    @Override
    public void delete(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream().map(TaskMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto getById(Integer id) {
        Optional<TaskEntity> task =  taskRepository.findById(id);
        return task.map(TaskMapper.INSTANCE::entityToDto).orElse(null);
    }

    @Override
    public void update(TaskDto request) {
        TaskEntity task = TaskMapper.INSTANCE.dtoToEntity(request);
        taskRepository.save(task);
    }
}
