package com.nuevospa.app.services.impl;

import com.nuevospa.app.entities.TaskStatus;
import com.nuevospa.app.repositories.TaskStatusRepository;
import com.nuevospa.app.services.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {
    private final TaskStatusRepository taskStatusRepository;

    @Autowired
    public TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    public TaskStatus getTaskStatusByName(String name) {
        return taskStatusRepository.findByName(name);
    }
}
