package com.previred.taskmanager.service;

import com.previred.taskmanager.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAllTasks();
    TaskDto getTaskById(Long id);
    TaskDto createTask(TaskDto taskDto);
    TaskDto updateTask(TaskDto taskDto);
    void deleteTask(Long id);
    TaskDto updateTaskStatus(Long id, String statusName);
    TaskDto updatePriority(Long id, String priorityName);
}
