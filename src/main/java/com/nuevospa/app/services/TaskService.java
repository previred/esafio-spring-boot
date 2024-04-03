package com.nuevospa.app.services;

import com.nuevospa.app.dtos.request.TaskRequestDto;
import com.nuevospa.app.entities.Task;

import java.util.List;

public interface TaskService {
    Task createTask(TaskRequestDto taskRequestDto);

    List<Task> getAllTasks();

    Task getTaskById(long id);

    Task updateTask(long id, TaskRequestDto taskRequestDto);

    Task deleteTask(long id);
}
