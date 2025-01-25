package com.example.desafio_spring_boot.service;

import com.example.desafio_spring_boot.domain.status_task.StatusTask;
import com.example.desafio_spring_boot.repository.StatusTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusTaskService {
    private final StatusTaskRepository statusTaskRepository;

    public StatusTaskService(StatusTaskRepository statusTaskRepository) {
        this.statusTaskRepository = statusTaskRepository;
    }

    public List<StatusTask> getAllStatusTasks() {
        return statusTaskRepository.findAll();
    }

    public Optional<StatusTask> getStatusTaskById(Long id) {
        return statusTaskRepository.findById(id);
    }

    public StatusTask createStatusTask(StatusTask statusTask) {
        return statusTaskRepository.save(statusTask);
    }
}
