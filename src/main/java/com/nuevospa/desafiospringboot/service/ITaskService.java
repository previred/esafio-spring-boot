package com.nuevospa.desafiospringboot.service;

import java.util.List;

import com.nuevospa.desafiospringboot.dto.TaskDTO;
import com.nuevospa.desafiospringboot.model.Task;

public interface ITaskService {
	TaskDTO saveTask(Task task);
    TaskDTO getTaskById(int id);
    List<TaskDTO> getAllTasks();
    TaskDTO updateTask(int id, Task task);
    void deleteTask(int id);
}
