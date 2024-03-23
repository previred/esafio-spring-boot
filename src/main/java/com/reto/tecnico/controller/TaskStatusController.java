package com.reto.tecnico.controller;

import com.reto.tecnico.security.services.JwtUtilService;
import com.reto.tecnico.services.ITaskService;
import org.openapitools.api.TaskStatusApi;
import org.openapitools.model.TaskResponse;
import org.openapitools.model.TaskStatusRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskStatusController implements TaskStatusApi {
  private final ITaskService taskService;
  private final JwtUtilService jwtUtilService;

  public TaskStatusController (ITaskService taskService, JwtUtilService jwtUtilService) {
    this.jwtUtilService = jwtUtilService;
    this.taskService = taskService;
  }

  @Override
  public ResponseEntity<List<TaskResponse>> getAllTaskStatus(String authorization){

    List<TaskResponse> lstTasks = taskService.getAllTasksWithStatusByUser(jwtUtilService.extractUsername(authorization.substring(7)));
    return ResponseEntity.ok(lstTasks);
  }

  @Override
  public ResponseEntity<TaskResponse> updateTaskStatus(String authorization, TaskStatusRequest taskStatusRequest){
    TaskResponse lstTasks = taskService.updateTaskStatus(authorization, taskStatusRequest);
    return ResponseEntity.ok(lstTasks);
  }

}
