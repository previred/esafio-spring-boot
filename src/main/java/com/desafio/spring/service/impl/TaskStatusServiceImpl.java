package com.desafio.spring.service.impl;

import com.desafio.spring.error.NotFoundException;
import com.desafio.spring.repository.TaskStatusRepository;
import com.desafio.spring.repository.dao.Task;
import com.desafio.spring.repository.dao.TaskStatus;
import com.desafio.spring.service.ITaskStatusService;
import org.springframework.stereotype.Service;

@Service
public class TaskStatusServiceImpl implements ITaskStatusService {

    private final TaskStatusRepository repository;

    TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository){
        this.repository = taskStatusRepository;
    }

    @Override
    public TaskStatus getTaskStatusByName(String name) {
        TaskStatus taskStatus = this.repository.findByNameStatusTask(name);
        if(taskStatus == null){
            throw new NotFoundException("Task Status not found");
        }
        return taskStatus;
    }
}
