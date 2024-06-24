package com.nuevospa.gestortareas.service;

import com.nuevospa.gestortareas.entity.TaskStatus;
import com.nuevospa.gestortareas.repository.TaskStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusService(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    public Optional<TaskStatus> getStatusByName(String name) {
        return taskStatusRepository.findByName(name);
    }
}
