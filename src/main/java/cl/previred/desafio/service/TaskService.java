package cl.previred.desafio.service;

import cl.previred.desafio.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TaskService {
    List<TaskEntity> findAll();

    TaskEntity createTask(TaskEntity task);

    TaskEntity findById(UUID id);

    TaskEntity update(UUID id, String description);

    TaskEntity delete(UUID id);

    TaskEntity updateTaskStatus(UUID id, String status);
}
