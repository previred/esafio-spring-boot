package com.desafio.gestion.service.impl;

import com.desafio.gestion.domain.Task;
import com.desafio.gestion.repository.TaskRepository;
import com.desafio.gestion.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }
}
