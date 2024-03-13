package cl.previred.challenge.controller;

import cl.previred.challenge.api.TasksApi;
import cl.previred.challenge.dto.TaskDto;
import cl.previred.challenge.mapper.TaskMapper;
import cl.previred.challenge.model.Task;
import cl.previred.challenge.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TaskController implements TasksApi {

  private final TaskService taskService;

  private final TaskMapper taskMapper;



  @Autowired
  public TaskController(TaskService taskService, TaskMapper taskMapper) {
    this.taskService = taskService;
      this.taskMapper = taskMapper;
  }

  @Override
  public ResponseEntity<TaskDto> tasksPost(@RequestBody TaskDto taskDto) {
    Task createdTask = taskService.saveTask(taskMapper.toEntity(taskDto));
    return new ResponseEntity<>(taskMapper.toDto(createdTask), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
    return taskService.findTaskById(id)
      .map(task -> new ResponseEntity<>(taskMapper.toDto(task), HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public ResponseEntity<List<TaskDto>> tasksGet() {
    List<TaskDto> tasks = taskService.findAllTasks().stream()
      .map(taskMapper::toDto)
      .collect(Collectors.toList());;
    return new ResponseEntity<>(tasks, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<TaskDto> tasksIdPut(@PathVariable Long id, @RequestBody TaskDto taskDto) {
    return taskService.updateTask(id, taskMapper.toEntity(taskDto))
      .map(task -> new ResponseEntity<>(taskMapper.toDto(task), HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public ResponseEntity<Void> tasksIdDelete(@PathVariable Long id) {
    taskService.deleteTask(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
