package com.desafio.gestion.service;

import com.desafio.gestion.dto.TaskDTO;
import com.desafio.gestion.dto.TaskPayload;

import java.util.List;

public interface TaskService {

    List<TaskDTO> findByUserId(Long userId);

    List<TaskDTO> findAll();
    TaskDTO findById(Long taskId);
    TaskDTO createTask(TaskPayload taskPayload);
    TaskDTO updateTask(Long taskId, TaskDTO task);
    void deleteTaskById(Long taskId);
}
