package org.openapitools.service.impl;

import org.openapitools.dto.Task;
import org.openapitools.repository.TaskRepository;
import org.openapitools.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getById(UUID taskId) {
        return taskRepository.getById(taskId);
    }

    @Override
    public void delete(UUID taskId) {
        taskRepository.delete(taskId);
    }
}
