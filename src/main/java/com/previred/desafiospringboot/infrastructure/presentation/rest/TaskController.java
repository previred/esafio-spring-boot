package com.previred.desafiospringboot.infrastructure.presentation.rest;

import com.previred.desafiospringboot.application.TaskService;
import com.previred.desafiospringboot.domain.model.Task;
import com.previred.desafiospringboot.infrastructure.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final JwtTokenProvider jwtTokenProvider;

    public TaskController(TaskService taskService, JwtTokenProvider jwtTokenProvider) {
        this.taskService = taskService;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        if (authToken == null || !jwtTokenProvider.validateToken(authToken)) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> taskList = taskService.getAllTasks();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable String status) {
        List<Task> taskList = taskService.getTasksByStatus(status);
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/creator/{userCreator}")
    public ResponseEntity<List<Task>> getTasksByUserCreator(@PathVariable String userCreator) {
        List<Task> taskList = taskService.getTasksByUserCreator(userCreator);
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/assigned/{userAssigned}")
    public ResponseEntity<List<Task>> getTasksByUserAssigned(@PathVariable String userAssigned) {
        List<Task> taskList = taskService.getTasksByUserAssigned(userAssigned);
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task, HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        if (authToken == null || !jwtTokenProvider.validateToken(authToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Task updatedTask = taskService.updateTask(task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id, HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        if (authToken == null || !jwtTokenProvider.validateToken(authToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

