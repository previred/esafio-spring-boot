package org.openapitools.service;

import org.openapitools.dto.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<Task> getAll();
    Task save(Task task);
    Task getById(UUID taskId);
    void delete(UUID taskId);
}
