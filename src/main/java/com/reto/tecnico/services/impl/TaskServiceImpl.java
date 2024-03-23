package com.reto.tecnico.services.impl;

import com.reto.tecnico.exceptions.CustomException;
import com.reto.tecnico.model.TaskStatus;
import com.reto.tecnico.model.Tasks;
import com.reto.tecnico.model.User;
import com.reto.tecnico.repository.ITaskRepository;
import com.reto.tecnico.repository.ITaskStatusRepository;
import com.reto.tecnico.repository.IUserRepository;
import com.reto.tecnico.services.ITaskService;
import com.reto.tecnico.util.Util;
import org.hibernate.Hibernate;
import org.openapitools.model.TaskRequest;
import org.openapitools.model.TaskResponse;
import org.openapitools.model.TaskStatusRequest;
import org.openapitools.model.TaskStatusResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {

  private final ITaskRepository taskRepository;
  private final IUserRepository userRepository;
  private final ITaskStatusRepository taskStatusRepository;

  public TaskServiceImpl(ITaskRepository taskRepository, IUserRepository userRepository, ITaskStatusRepository taskStatusRepository) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;
    this.taskStatusRepository = taskStatusRepository;
  }
  @Override
  @Transactional
  public TaskResponse createTask(TaskRequest taskRequest, String username) {
    Optional<User> optionalUser = userRepository.findByUsername(username);

    optionalUser.orElseThrow(() -> new CustomException("No se encontró el recurso solicitado"));

    taskRepository.findByTitle(taskRequest.getTitle())
            .ifPresent(task -> {
              throw new CustomException("La tarea ya está registrada.");
            });

    Tasks task = Tasks.builder()
            .title(taskRequest.getTitle())
            .description(taskRequest.getDescription())
            .user(optionalUser.get())
            .build();

    task = taskRepository.save(task);

    TaskStatus initialStatus = new TaskStatus();
    initialStatus.setStatus("POR INICIAR");
    initialStatus.setLastModified(LocalDateTime.now());
    initialStatus.setTask(task);

    taskStatusRepository.save(initialStatus);

    return buildTaskResponse(task);
  }
  @Override
  public List<TaskResponse> getAllTask() {
    List<Tasks> lstTasks = taskRepository.findAll();

    return lstTasks.stream()
            .map(this::buildTaskResponse)
            .collect(Collectors.toList());
  }


  @Override
  public TaskResponse getTaskById(Long id) {
    Tasks task = taskRepository.findByTaskId(id)
            .orElseThrow(() -> new CustomException("No se encontró el recurso solicitado."));

    return buildTaskResponse(task);
  }

  @Override
  public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
    Optional<Tasks> optionalTasks = taskRepository.findByTaskId(id);
    Tasks task = optionalTasks.orElseThrow(() -> new CustomException("No se encontró el recurso solicitado"));

    BeanUtils.copyProperties(taskRequest, task, Util.getNullPropertyNames(taskRequest));

    Tasks updatedTask = taskRepository.save(task);

    return buildTaskResponse(updatedTask);
  }

  @Override
  public List<TaskResponse> getAllTasksWithStatusByUser(String username) {
    Optional<User> optionalUser = userRepository.findByUsername(username);
    User user = optionalUser.orElseThrow(() -> new CustomException("No se encontró el recurso solicitado"));

    List<Tasks> tasks = taskRepository.findAllByUser(user);

    return tasks.stream()
            .map(task -> {
              TaskResponse taskResponse = new TaskResponse();
              taskResponse.setId(task.getTaskId());
              taskResponse.setTitle(task.getTitle());
              taskResponse.setDescription(task.getDescription());

              List<TaskStatusResponse> taskStatusResponses = task.getLstTaskStatus().stream()
                      .map(taskStatus -> {
                        TaskStatusResponse statusResponse = new TaskStatusResponse();
                        statusResponse.setTaskStatusId(taskStatus.getTaskStatusId().intValue());
                        statusResponse.setStatus(taskStatus.getStatus());
                        statusResponse.setLastModified(taskStatus.getLastModified().toString());
                        return statusResponse;
                      })
                      .collect(Collectors.toList());

              taskResponse.setLstTaskStatus(taskStatusResponses);
              return taskResponse;
            })
            .collect(Collectors.toList());
  }

  @Override
  public TaskResponse updateTaskStatus(String authorization, TaskStatusRequest taskStatusRequest) {
    Optional<Tasks> optionalTasks = taskRepository.findByTaskId(Long.valueOf(taskStatusRequest.getIdTask()));
    Tasks tasks = optionalTasks.orElseThrow(() -> new CustomException("No se encontró el recurso solicitado"));

    TaskStatus newTaskStatus = new TaskStatus();
    newTaskStatus.setStatus(taskStatusRequest.getStatus());
    newTaskStatus.setLastModified(LocalDateTime.now());
    newTaskStatus.setTask(tasks);

    TaskStatus savedTaskStatus = taskStatusRepository.save(newTaskStatus);

    return Optional.of(savedTaskStatus).map(taskStatus -> {
      TaskResponse taskResponse = new TaskResponse();
      taskResponse.setId(taskStatus.getTask().getTaskId());
      taskResponse.setTitle(taskStatus.getTask().getTitle());
      taskResponse.setDescription(taskStatus.getTask().getDescription());

      TaskStatusResponse taskStatusResponse = new TaskStatusResponse();
      taskStatusResponse.setTaskStatusId(taskStatus.getTaskStatusId().intValue());
      taskStatusResponse.setStatus(taskStatus.getStatus());
      taskStatusResponse.setLastModified(taskStatus.getLastModified().toString());

      taskResponse.setLstTaskStatus(Collections.singletonList(taskStatusResponse));

      return taskResponse;
    }).orElseThrow(() -> new CustomException("No se pudo guardar el estado de la tarea"));
  }


  private TaskResponse buildTaskResponse(Tasks task) {
    TaskResponse taskResponse = new TaskResponse();
    taskResponse.setId(task.getTaskId());
    taskResponse.setDescription(task.getDescription());
    taskResponse.setTitle(task.getTitle());
    return taskResponse;
  }
}
