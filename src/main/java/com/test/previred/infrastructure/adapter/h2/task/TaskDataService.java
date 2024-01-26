package com.test.previred.infrastructure.adapter.h2.task;

import com.test.previred.domain.model.task.Task;
import com.test.previred.domain.model.task.gateway.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskDataService implements TaskRepository {

    private final TaskDataRepository taskDataRepository;
    private final TaskStatusDataRepository taskStatusDataRepository;


    @Override
    public Task save(Task task) {
        Optional<TaskStatusEntity> status = taskStatusDataRepository.findById(Long.valueOf(task.getStatus()));

        TaskEntity taskEntity = TaskMapper.toEntity(task);
        taskEntity.setStatus(status.get());
        taskEntity = taskDataRepository.save(taskEntity);
        return TaskMapper.toDomain(taskEntity);
    }

    @Override
    public List<Task> findAll() {
        return taskDataRepository.findAll().stream().map(TaskMapper::toDomain).toList();
    }

    @Override
    public Task findById(String id) {

        return taskDataRepository.findById(Long.valueOf(id)).map(TaskMapper::toDomain).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        try {
            // Intentar eliminar la tarea por su ID
            taskDataRepository.deleteById(Long.valueOf(id));
        } catch (EmptyResultDataAccessException ex) {
            // Lanzar una excepción personalizada si la tarea no existe
            throw new Exceptions.TaskNotFoundException(id);
        }
    }

    @Override
    public Task update(Task task) {
        TaskEntity taskEntity = taskDataRepository
                .findById(Long.valueOf(task.getId()))
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Task not found"));
        assert taskEntity != null;
        taskEntity.setDescription(task.getDescription());
        Optional<TaskStatusEntity> status = taskStatusDataRepository.findById(Long.valueOf(task.getStatus()));

        taskEntity.setStatus(status.get());
        return TaskMapper.toDomain(taskDataRepository.save(taskEntity));
    }
}

