package com.nuevospa.taskmanager.controller;

import io.swagger.annotations.ApiOperation;
import com.nuevospa.taskmanager.dto.Task;
import org.springframework.http.*;
import com.nuevospa.taskmanager.service.TaskService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ApiOperation("get tasks Service")
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/task-id/{taskId}")
    @ApiOperation("get task by ID ")
    public ResponseEntity<Task> getById(@PathVariable UUID taskId) {
        return ResponseEntity.ok(taskService.getById(taskId));
    }

    @PostMapping("/save")
    @ApiOperation("save task service")
    public ResponseEntity<Task> save(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.save(task), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{taskId}")
    @ApiOperation("delete task by id service")
    public ResponseEntity<Void> delete(@PathVariable UUID taskId) {
        taskService.delete(taskId);
        return ResponseEntity.ok().build();
    }
}
