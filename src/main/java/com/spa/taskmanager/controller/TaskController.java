package com.spa.taskmanager.controller;

import com.spa.taskmanager.service.TaskService;
import com.spa.taskmanager.model.Task;
import com.spa.taskmanager.mapper.TaskMapper;
import org.openapitools.api.TasksApi;
import org.openapitools.model.TaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController implements TasksApi {

    @Autowired
    private TaskService taskService;

    @Override
    public ResponseEntity<Void> tasksDelete(@NotNull Integer id) {
        taskService.deleteTask(id.longValue());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.Task>> tasksGet() {
        List<Task> domainTasks = taskService.getAllTasks();
        List<org.openapitools.model.Task> apiTasks = domainTasks.stream()
                .map(TaskMapper::toApiModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(apiTasks);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Task> tasksPost(@Valid @RequestBody TaskRequest taskRequest) {
        Task domainTask = TaskMapper.toDomainModel(taskRequest);
        Task createdTask = taskService.createTask(domainTask);
        org.openapitools.model.Task apiTask = TaskMapper.toApiModel(createdTask);
        return ResponseEntity.status(201).body(apiTask);
    }

    @Override
    public ResponseEntity<org.openapitools.model.Task> tasksPut(@Valid @RequestBody TaskRequest taskRequest) {
        Task domainTask = TaskMapper.toDomainModel(taskRequest);
        Task updatedTask = taskService.updateTask(domainTask.getId(), domainTask);
        org.openapitools.model.Task apiTask = TaskMapper.toApiModel(updatedTask);
        return ResponseEntity.ok(apiTask);
    }
}
