package com.example.desafio_spring_boot.controller;

import com.example.desafio_spring_boot.domain.status_task.StatusNames;
import com.example.desafio_spring_boot.domain.status_task.StatusTask;
import com.example.desafio_spring_boot.domain.status_task.StatusTaskRequestDto;
import com.example.desafio_spring_boot.domain.task.Task;
import com.example.desafio_spring_boot.domain.task.TaskRequestDto;
import com.example.desafio_spring_boot.domain.task.TaskResponseDto;
import com.example.desafio_spring_boot.service.StatusTaskService;
import com.example.desafio_spring_boot.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final StatusTaskService statusTaskService;

    public TaskController(TaskService taskService, StatusTaskService statusTaskService) {
        this.taskService = taskService;
        this.statusTaskService = statusTaskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> taskResponseDtos = taskService.getAllTasks().stream()
                .map(task -> new TaskResponseDto(task))
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(task -> ResponseEntity.ok(new TaskResponseDto(task)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequestDto task) {
        Task createdTask = taskService.createTask(task.toTask());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();
        StatusTaskRequestDto statusTask = new StatusTaskRequestDto();
        statusTask.setTaskId(createdTask.getId());
        statusTask.setStatusName(StatusNames.CREATED);
        statusTaskService.createStatusTask(statusTask.toStatusTask());
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<Task> createStatusTask(@Valid @RequestBody StatusTaskRequestDto statusTask) {
        StatusTask createdStatusTask = statusTaskService.createStatusTask(statusTask.toStatusTask());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdStatusTask.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto task) {
        Task updatedTask = taskService.updateTask(task.toTask(id));
        return ResponseEntity.ok(new TaskResponseDto(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
