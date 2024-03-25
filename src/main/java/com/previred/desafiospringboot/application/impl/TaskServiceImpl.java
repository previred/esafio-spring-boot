package com.previred.desafiospringboot.application.impl;

import com.previred.desafiospringboot.application.TaskService;
import com.previred.desafiospringboot.domain.model.Task;
import com.previred.desafiospringboot.infrastructure.persistance.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> getTasksByUserCreator(String userCreator) {
        return taskRepository.findByUserCreator(userCreator);
    }

    @Override
    public List<Task> getTasksByUserAssigned(String userAssigned) {
        return taskRepository.findByUserAssigned(userAssigned);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
