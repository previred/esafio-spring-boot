package cl.nuevo.spa.taskmanager.controller.rest;

import cl.nuevo.spa.taskmanager.domain.dto.TaskDto;
import cl.nuevo.spa.taskmanager.service.TaskService;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

  private static final Long TASK_ID = 1L;
  @Mock private TaskService taskService;
  @InjectMocks private TaskController taskController;

  @Test
  void shouldReturnAllTaskWhenSearchAllTask() {
    TaskDto firstTaskDto = TaskDto.builder().build();
    TaskDto secondTaskDto = TaskDto.builder().build();
    List<TaskDto> tasksDto = List.of(firstTaskDto, secondTaskDto);

    Mockito.when(taskService.getAllTasks()).thenReturn(tasksDto);
    ResponseEntity<List<TaskDto>> tasksDtoResponseEntity = taskController.getAllTasks();
    Assertions.assertEquals(
        HttpStatus.OK, tasksDtoResponseEntity.getStatusCode(), "should return 200 when exist task");
    Assertions.assertEquals(
        tasksDto.size(),
        Objects.requireNonNull(tasksDtoResponseEntity.getBody()).size(),
        "should return all task expected");
    Assertions.assertEquals(
        tasksDto, tasksDtoResponseEntity.getBody(), "Should be the same tasks as expected");
  }

  @Test
  void shouldReturnTaskWhenSearchTaskById() {
    TaskDto taskDTO = TaskDto.builder().build();
    Mockito.when(taskService.getTaskById(Mockito.anyLong())).thenReturn(taskDTO);
    ResponseEntity<TaskDto> taskDtoResponseEntity = taskController.getTaskById(TASK_ID);
    Assertions.assertEquals(
        HttpStatus.OK,
        taskDtoResponseEntity.getStatusCode(),
        "should return 200 when search all task its ok");
    Assertions.assertEquals(
        taskDTO, taskDtoResponseEntity.getBody(), "should be equals to expected task");
  }

  @Test
  void shouldCreateTaskWhenRequestItsOk() {
    TaskDto taskDTO = TaskDto.builder().build();
    Mockito.when(taskService.createTask(Mockito.any(TaskDto.class))).thenReturn(taskDTO);
    ResponseEntity<TaskDto> taskDtoResponseEntity = taskController.createTask(taskDTO);
    Assertions.assertEquals(
        HttpStatus.CREATED,
        taskDtoResponseEntity.getStatusCode(),
        "should return 201 when task its created");
    Assertions.assertEquals(
        taskDTO, taskDtoResponseEntity.getBody(), "should be equals to expected task");
  }

  @Test
  void shouldUpdateTaskWhenRequestItsOk() {
    TaskDto taskDTO = TaskDto.builder().build();
    Mockito.when(taskService.updateTask(Mockito.any(TaskDto.class))).thenReturn(taskDTO);
    ResponseEntity<TaskDto> taskDtoResponseEntity = taskController.updateTask(taskDTO);
    Assertions.assertEquals(
        HttpStatus.OK,
        taskDtoResponseEntity.getStatusCode(),
        "should return 200 when task its created");
    Assertions.assertEquals(
        taskDTO, taskDtoResponseEntity.getBody(), "should be equals to expected task");
  }

  @Test
  void shouldDeleteTaskWhenTaskExist() {
    ResponseEntity<?> taskDtoResponseEntity = taskController.deleteTask(TASK_ID);
    Assertions.assertEquals(
        HttpStatus.NO_CONTENT,
        taskDtoResponseEntity.getStatusCode(),
        "should return 204 when task its deleted");
  }
}
