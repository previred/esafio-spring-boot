package com.test.moveapps.service;

import com.test.moveapps.domain.dto.TaskDto;
import com.test.moveapps.domain.entity.Task;
import com.test.moveapps.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public TaskDto addTask(String taskName) {

        Task task = new Task();
        task.setTask_name(taskName);

        return new TaskDto().convertTask(taskRepository.save(task));
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(task -> {
            return new TaskDto(task.getId(), task.getTask_name());
        }).toList();
    }

    @Override
    public Optional<Task> findTaskById(Long taskId) {
        return taskRepository.findTaskById(taskId);
    }

}
