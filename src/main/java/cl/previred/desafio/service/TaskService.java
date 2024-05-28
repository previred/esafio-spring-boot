package cl.previred.desafio.service;

import cl.previred.desafio.entity.TaskEntity;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskEntity> findAll();

    TaskEntity createTask(TaskEntity task);

    TaskEntity findById(UUID id);

    TaskEntity update(UUID id, TaskEntity old);

    TaskEntity delete(UUID id);
}
