package com.test.previred.infrastructure.rest.controller.task;

import com.test.previred.domain.model.task.Task;
import com.test.previred.domain.usecase.task.TaskUseCase;
import com.test.previred.infrastructure.rest.controller.common.Response;
import com.test.previred.infrastructure.rest.controller.task.request.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private final TaskUseCase taskUseCase;


    @PostMapping()
    public ResponseEntity<Response<Task>> create(@RequestBody TaskRequest taskRequest) {
        final Response<Task> taskResponse = new Response<>();
        taskResponse.setData(taskUseCase.saveTask(taskRequest.toTask()));
        taskResponse.setMessage(Collections.singletonList("User created successfully"));
        taskResponse.setStatus(HttpStatus.CREATED);
        taskResponse.setDate(LocalDateTime.now());
        return ResponseEntity.status(taskResponse.getStatus()).body(taskResponse);
    }


    @GetMapping()
    public ResponseEntity<Response<List<Task>>> findAll() {
        final Response<List<Task>> taskResponse = new Response<>();
        List<Task> taskList = taskUseCase.findAll();
        taskResponse.setCount((long) taskList.size());
        taskResponse.setData(taskList);
        taskResponse.setMessage(Collections.singletonList("List of task"));
        taskResponse.setStatus(HttpStatus.FOUND);
        taskResponse.setDate(LocalDateTime.now());
        return ResponseEntity.status(taskResponse.getStatus()).body(taskResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Task>> findById(@PathVariable String id) {
        final Response<Task> taskResponse = new Response<>();
        Task task = taskUseCase.findById(id);
        taskResponse.setData(task);
        taskResponse.setMessage(Collections.singletonList("Task found"));
        taskResponse.setStatus(HttpStatus.FOUND);
        taskResponse.setDate(LocalDateTime.now());
        return ResponseEntity.status(taskResponse.getStatus()).body(taskResponse);
    }


    @PutMapping()
    public ResponseEntity<Response<Task>> update(@RequestBody TaskRequest taskRequest) {
        final Response<Task> taskResponse = new Response<>();
        Task task = taskUseCase.update(taskRequest.toTask());
        taskResponse.setData(task);
        taskResponse.setMessage(Collections.singletonList("Task updated"));
        taskResponse.setStatus(HttpStatus.OK);
        taskResponse.setDate(LocalDateTime.now());
        return ResponseEntity.status(taskResponse.getStatus()).body(taskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Task>> deleteById(@PathVariable String id) {
        final Response<Task> taskResponse = new Response<>();
        taskUseCase.deleteById(id);
        taskResponse.setMessage(Collections.singletonList("Task deleted"));
        taskResponse.setStatus(HttpStatus.OK);
        taskResponse.setDate(LocalDateTime.now());
        return ResponseEntity.status(taskResponse.getStatus()).body(taskResponse);
    }

}
