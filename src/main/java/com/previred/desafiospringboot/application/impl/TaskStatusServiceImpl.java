package com.previred.desafiospringboot.application.impl;

import com.previred.desafiospringboot.application.TaskStatusService;
import com.previred.desafiospringboot.domain.model.TaskStatus;
import com.previred.desafiospringboot.infrastructure.persistance.TaskStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public List<TaskStatus> getAllTaskStatus() {
        return taskStatusRepository.findAll();
    }
}

