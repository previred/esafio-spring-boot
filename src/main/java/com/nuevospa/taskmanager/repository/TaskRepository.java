package com.nuevospa.taskmanager.repository;

import com.nuevospa.taskmanager.dto.Task;

import java.util.*;

public interface TaskRepository {
    List<Task> getAll();
    Task save(Task task);
    Task getById(UUID taskId);
    void delete(UUID taskId);
}
