package com.desafio.gestion.service.impl;

import com.desafio.gestion.domain.Task;
import com.desafio.gestion.domain.TaskStatus;
import com.desafio.gestion.domain.User;
import com.desafio.gestion.dto.TaskDTO;
import com.desafio.gestion.dto.TaskPayload;
import com.desafio.gestion.repository.TaskRepository;
import com.desafio.gestion.repository.TaskStatusRepository;
import com.desafio.gestion.repository.UserRepository;
import com.desafio.gestion.service.TaskService;
import com.desafio.gestion.service.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository, TaskStatusRepository taskStatusRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public List<TaskDTO> findByUserId(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long taskId) {
        return taskMapper.toDto(taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId)));
    }

    @Override
    public List<TaskDTO> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.tasksToTaskDtos(tasks);
    }

    @Override
    public TaskDTO createTask(TaskPayload taskPayload) {
        User user = userRepository.findById(taskPayload.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + taskPayload.getUserId()));

        TaskStatus status = taskStatusRepository.findByName(taskPayload.getStatus())
                .orElseThrow(() -> new NoSuchElementException("TaskStatus not found with name: " + taskPayload.getStatus()));

        Task task = Task.builder()
                .title(taskPayload.getTitle())
                .description(taskPayload.getDescription())
                .dueDate(taskPayload.getDueDate())
                .user(user)
                .status(status).build();

        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO taskDto) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

        if (!taskId.equals(taskDto.getId())) {
            throw new IllegalArgumentException("Task ID in path must match task ID in request body");
        }

        User user = userRepository.findById(taskDto.getUser().getId())
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + taskDto.getUser().getId()));

        TaskStatus status = taskStatusRepository.findByName(taskDto.getStatus())
                .orElseThrow(() -> new NoSuchElementException("TaskStatus not found with name: " + taskDto.getStatus()));

        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setDueDate(taskDto.getDueDate());
        existingTask.setUser(user);
        existingTask.setStatus(status);
        Task savedTask = taskRepository.save(existingTask);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public void deleteTaskById(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new NoSuchElementException("Task not found with ID: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }
}
