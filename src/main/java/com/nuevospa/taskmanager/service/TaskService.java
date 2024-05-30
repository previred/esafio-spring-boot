package com.nuevospa.taskmanager.service;

import com.nuevospa.taskmanager.dto.Task;

import java.util.*;

public interface TaskService {

    List<Task> getAll();
    Task save(Task task);
    Task getById(UUID taskId);
    void delete(UUID taskId);
}
