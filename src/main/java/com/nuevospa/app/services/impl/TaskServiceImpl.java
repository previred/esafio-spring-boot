package com.nuevospa.app.services.impl;

import com.nuevospa.app.dtos.request.TaskRequestDto;
import com.nuevospa.app.entities.Task;
import com.nuevospa.app.entities.TaskStatus;
import com.nuevospa.app.exceptions.DataNotFoundException;
import com.nuevospa.app.mappers.EntityMapper;
import com.nuevospa.app.repositories.TaskRepository;
import com.nuevospa.app.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusServiceImpl taskStatusService;
    private final EntityMapper entityMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskStatusServiceImpl taskStatusService, EntityMapper entityMapper) {
        this.taskRepository = taskRepository;
        this.taskStatusService = taskStatusService;
        this.entityMapper = entityMapper;
    }

    public Task createTask(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        entityMapper.mapDTOToEntity(taskRequestDto, task);
        String status = taskRequestDto.getStatus();
        TaskStatus taskStatus = (status != null) ? getTaskStatusByName(status) : getDefaultTaskStatus();
        task.setTaskStatus(taskStatus);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Task with id  " + id + " not found."));
    }

    public Task updateTask(long id, TaskRequestDto taskRequestDto) {
        Task task = getTaskById(id);
        entityMapper.mapDTOToEntity(taskRequestDto, task);
        String status = taskRequestDto.getStatus();
        if (status != null) {
            TaskStatus taskStatus = getTaskStatusByName(status);
            task.setTaskStatus(taskStatus);
        }
        return taskRepository.save(task);
    }

    public Task deleteTask(long id) {
        Task task = getTaskById(id);
        task.setDeleteAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    private TaskStatus getTaskStatusByName(String statusName) {
        return taskStatusService.getTaskStatusByName(statusName);
    }

    private TaskStatus getDefaultTaskStatus() {
        return getTaskStatusByName("Pending");
    }

}