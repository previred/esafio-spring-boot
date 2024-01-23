package cl.nuevo.spa.taskmanager.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import cl.nuevo.spa.taskmanager.domain.dto.TaskDto;
import cl.nuevo.spa.taskmanager.domain.entity.TaskEntity;
import cl.nuevo.spa.taskmanager.domain.exception.NotFoundTaskException;
import cl.nuevo.spa.taskmanager.mapper.TaskDtoToTaskEntityMapper;
import cl.nuevo.spa.taskmanager.mapper.TaskEntityToTaskDtoMapper;
import cl.nuevo.spa.taskmanager.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

  @Mock private TaskRepository taskRepository;
  @Mock private TaskEntityToTaskDtoMapper taskEntityToTaskDtoMapper;
  @Mock private TaskDtoToTaskEntityMapper taskDtoToTaskEntityMapper;

  @InjectMocks private TaskService taskService;

  @Test
  void shouldRetrieveAllTasksOk() {
    TaskEntity firstTaskEntity = TaskEntity.builder().build();
    TaskEntity secondTaskEntity = TaskEntity.builder().build();
    List<TaskEntity> tasksEntity = List.of(firstTaskEntity, secondTaskEntity);
    when(taskRepository.findAll()).thenReturn(tasksEntity);
    TaskDto firstTaskDto = TaskDto.builder().build();
    TaskDto secondTaskDto = TaskDto.builder().build();
    List<TaskDto> expected = List.of(firstTaskDto, secondTaskDto);
    when(taskEntityToTaskDtoMapper.map(firstTaskEntity)).thenReturn(firstTaskDto);
    when(taskEntityToTaskDtoMapper.map(secondTaskEntity)).thenReturn(secondTaskDto);
    Assertions.assertEquals(expected, taskService.getAllTasks());
    Mockito.verify(taskRepository, Mockito.times(1)).findAll();
    Mockito.verify(taskEntityToTaskDtoMapper, Mockito.times(2)).map(any(TaskEntity.class));
  }

  @Test
  void shouldRetrieveTaskById() {
    TaskEntity taskEntity = TaskEntity.builder().build();
    TaskDto taskDto = TaskDto.builder().build();
    when(taskRepository.findById(anyLong())).thenReturn(Optional.of(taskEntity));
    when(taskEntityToTaskDtoMapper.map(taskEntity)).thenReturn(taskDto);
    Assertions.assertEquals(taskDto, taskService.getTaskById(anyLong()));
    Mockito.verify(taskRepository, Mockito.times(1)).findById(anyLong());
    Mockito.verify(taskEntityToTaskDtoMapper, Mockito.times(1)).map(any(TaskEntity.class));
  }

  @Test
  void shouldThrowExceptionWhenTaskIdNotFoundInDb() {
    when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());
    Assertions.assertThrows(NotFoundTaskException.class, () -> taskService.getTaskById(anyLong()));
    Mockito.verify(taskRepository, Mockito.times(1)).findById(anyLong());
    Mockito.verify(taskEntityToTaskDtoMapper, Mockito.times(0)).map(any(TaskEntity.class));
  }

  @Test
  void shouldReturnTaskWhenCreateTaskItsOk() {
    TaskEntity taskEntity = TaskEntity.builder().build();
    TaskDto expected = TaskDto.builder().build();
    TaskDto taskDto = TaskDto.builder().build();
    when(taskRepository.save(taskEntity)).thenReturn(taskEntity);
    when(taskDtoToTaskEntityMapper.map(taskDto)).thenReturn(taskEntity);
    when(taskEntityToTaskDtoMapper.map(taskEntity)).thenReturn(expected);
    Assertions.assertEquals(expected, taskService.createTask(taskDto));
    Mockito.verify(taskRepository, Mockito.times(1)).save(any(TaskEntity.class));
    Mockito.verify(taskEntityToTaskDtoMapper, Mockito.times(1)).map(any(TaskEntity.class));
    Mockito.verify(taskDtoToTaskEntityMapper, Mockito.times(1)).map(any(TaskDto.class));
  }

  @Test
  void shouldUpdateTaskWhenTaskExist() {
    TaskEntity taskEntity = TaskEntity.builder().id(0L).build();
    TaskDto taskDto = TaskDto.builder().id(0L).build();
    when(taskRepository.findById(anyLong())).thenReturn(Optional.of(taskEntity));
    when(taskRepository.save(taskEntity)).thenReturn(taskEntity);
    when(taskDtoToTaskEntityMapper.map(taskDto, taskEntity)).thenReturn(taskEntity);
    when(taskEntityToTaskDtoMapper.map(taskEntity)).thenReturn(taskDto);
    Assertions.assertEquals(taskDto, taskService.updateTask((taskDto)));
    Mockito.verify(taskRepository, Mockito.times(1)).findById(anyLong());
    Mockito.verify(taskRepository, Mockito.times(1)).save(any(TaskEntity.class));
    Mockito.verify(taskEntityToTaskDtoMapper, Mockito.times(1)).map(any(TaskEntity.class));
    Mockito.verify(taskDtoToTaskEntityMapper, Mockito.times(1))
        .map(any(TaskDto.class), any(TaskEntity.class));
  }

  @Test
  void shouldThrowExceptionWhenTryUpdateTaskAndNotExist() {
    when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());
    Assertions.assertThrows(NotFoundTaskException.class, () -> taskService.getTaskById(anyLong()));
    Mockito.verify(taskRepository, Mockito.times(1)).findById(anyLong());
    Mockito.verify(taskEntityToTaskDtoMapper, Mockito.times(0)).map(any(TaskEntity.class));
  }

  @Test
  void shouldDeleteTaskWhenTaskExist() {
    when(taskRepository.existsById(anyLong())).thenReturn(true);
    doNothing().when(taskRepository).deleteById(anyLong());
    taskService.deleteTask(anyLong());
    Mockito.verify(taskRepository, Mockito.times(1)).existsById(anyLong());
    Mockito.verify(taskRepository, Mockito.times(1)).deleteById(anyLong());
  }

  @Test
  void shouldTrowExceptionWhenTryDeleteTaskAndTaskNotExist() {
    when(taskRepository.existsById(anyLong())).thenReturn(false);
    Assertions.assertThrows(NotFoundTaskException.class, () -> taskService.deleteTask(anyLong()));
    Mockito.verify(taskRepository, Mockito.times(1)).existsById(anyLong());
    Mockito.verify(taskRepository, Mockito.times(0)).deleteById(anyLong());
  }
}
