package cl.previred.challenge.controller;

import cl.previred.challenge.api.TaskStatusApi;
import cl.previred.challenge.dto.TaskStatusDto;
import cl.previred.challenge.mapper.TaskStatusMapper;
import cl.previred.challenge.model.TaskStatus;
import cl.previred.challenge.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskStatusController implements TaskStatusApi {

  private final TaskStatusService taskStatusService;

  private final TaskStatusMapper taskStatusMapper;

  @Autowired
  public TaskStatusController(TaskStatusService taskStatusService, TaskStatusMapper taskStatusMapper) {
    this.taskStatusService = taskStatusService;
      this.taskStatusMapper = taskStatusMapper;
  }

  @Override
  public ResponseEntity<TaskStatusDto> taskStatusPost(@RequestBody TaskStatusDto taskStatus) {
    TaskStatus createdTaskStatus = taskStatusService.saveTaskStatus(taskStatusMapper.toEntity(taskStatus));
    return new ResponseEntity<>(taskStatusMapper.toDto(createdTaskStatus), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<TaskStatusDto> taskStatusTaskStatusIdGet(@PathVariable Long taskStatusId) {
    return taskStatusService.findById(taskStatusId)
      .map(taskStatus -> new ResponseEntity<>(taskStatusMapper.toDto(taskStatus), HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public ResponseEntity<List<TaskStatusDto>> taskStatusGet() {
    List<TaskStatusDto> taskStatuses = taskStatusService.findAll().stream()
      .map(taskStatusMapper::toDto)
      .collect(Collectors.toList());;
    return new ResponseEntity<>(taskStatuses, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<TaskStatusDto> taskStatusTaskStatusIdPut(@PathVariable Long taskStatusId, @RequestBody TaskStatusDto taskStatusDto) {
    return taskStatusService
      .updateTaskStatus(taskStatusId, taskStatusMapper.toEntity(taskStatusDto))
      .map(updatedTaskStatus -> new ResponseEntity<>(taskStatusMapper.toDto(updatedTaskStatus), HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public ResponseEntity<Void> taskStatusTaskStatusIdDelete(@PathVariable Long taskStatusId) {
    if (taskStatusService.findById(taskStatusId).isPresent()) {
      taskStatusService.deleteById(taskStatusId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
