package com.reto.tecnico.services;

import com.reto.tecnico.model.Tasks;
import com.reto.tecnico.model.User;
import org.openapitools.model.TaskRequest;
import org.openapitools.model.TaskResponse;
import org.openapitools.model.TaskStatusRequest;

import java.util.List;

public interface ITaskService {

  TaskResponse createTask(TaskRequest taskRequest, String username);
  List<TaskResponse> getAllTask();
  TaskResponse getTaskById(Long id);
  TaskResponse updateTask(Long id, TaskRequest taskRequest);
  List<TaskResponse>  getAllTasksWithStatusByUser(String username);
  TaskResponse updateTaskStatus (String authorization, TaskStatusRequest taskStatusRequest);
}
