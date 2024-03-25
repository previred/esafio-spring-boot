package com.previred.desafiospringboot.application;

import com.previred.desafiospringboot.domain.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);

    List<Task> getAllTasks();

    List<Task> getTasksByStatus(String status);

    List<Task> getTasksByUserCreator(String userCreator);

    List<Task> getTasksByUserAssigned(String userAssigned);

    Task updateTask(Task task);

    void deleteTask(Integer id);
}
