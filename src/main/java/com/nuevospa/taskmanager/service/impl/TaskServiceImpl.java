package com.nuevospa.taskmanager.service.impl;

import com.nuevospa.taskmanager.dto.Task;
import com.nuevospa.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;
import com.nuevospa.taskmanager.repository.TaskRepository;


import java.util.*;

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
