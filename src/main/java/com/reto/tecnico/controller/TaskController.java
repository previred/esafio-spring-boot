package com.reto.tecnico.controller;

import com.reto.tecnico.security.services.JwtUtilService;
import com.reto.tecnico.services.ITaskService;
import org.openapitools.api.TasksApi;
import org.openapitools.model.TaskRequest;
import org.openapitools.model.TaskResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController implements TasksApi {

  private final ITaskService taskService;

  private final JwtUtilService jwtUtilService;

  public TaskController (ITaskService taskService, JwtUtilService jwtUtilService) {
    this.taskService = taskService;
    this.jwtUtilService = jwtUtilService;
  }

  @Override
  public ResponseEntity<TaskResponse> createTask(String authorization, TaskRequest taskRequest) {

    TaskResponse taskResponse = taskService.createTask(taskRequest, jwtUtilService.extractUsername(authorization.substring(7)));

    return ResponseEntity.ok(taskResponse);
  }

  @Override
  public ResponseEntity<List<TaskResponse>> getAllTask () {

    List<TaskResponse> lstTask = taskService.getAllTask();
    return ResponseEntity.ok(lstTask);
  }

  @Override
  public ResponseEntity<TaskResponse> getTaskById(Long taskId) {
    TaskResponse taskResponse = taskService.getTaskById(taskId);
    return ResponseEntity.ok(taskResponse);
  }

  @Override
  public ResponseEntity<TaskResponse> updateTask(Long taskId, TaskRequest taskRequest) {
    TaskResponse taskResponse = taskService.updateTask(taskId, taskRequest);
    return ResponseEntity.ok(taskResponse);
  }

}
