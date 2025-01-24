package cl.previred.tasksapi.service.impl;

import cl.previred.tasksapi.mappers.TaskMapper;
import cl.previred.tasksapi.model.TaskModel;
import cl.previred.tasksapi.model.TaskStatusModel;
import cl.previred.tasksapi.repository.TaskRepository;
import cl.previred.tasksapi.repository.TaskStatusRepository;
import cl.previred.tasksapi.service.TasksService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.Task;
import org.openapitools.model.TaskInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TasksServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskStatusRepository taskStatusRepository;

    private final static String DEFAULT_TASK_NAME = "Tarea01";

    private final TaskMapper mapper = TaskMapper.INSTANCE;

    private TasksService service;

    TaskModel mockTaskModel;

    List<TaskModel> mockListModel;

    TaskStatusModel mockTaskStatusModel;

    Task mockDTO;

    List<Task> mockListDTO;

    TaskInput mockInputDTO;


    @BeforeEach
    void setUp() {
        service = new TasksServiceImpl(taskRepository, taskStatusRepository);
        mockTaskStatusModel = TaskStatusModel.builder().taskStatusId(1).taskStatusName("EJECUTANDO").build();

        mockTaskModel = TaskModel.builder().taskId(1).taskName(DEFAULT_TASK_NAME).taskDescription("Tarea Prueba 01").taskStatus(mockTaskStatusModel).build();
        mockListModel = new ArrayList<>();
        mockListModel.add(TaskModel.builder().taskId(1).taskName("Tarea Prueba 01").taskDescription("Tarea Prueba 01").taskStatus(mockTaskStatusModel).build());
        mockListModel.add(TaskModel.builder().taskId(1).taskName("Tarea Prueba 02").taskDescription("Tarea Prueba 02").taskStatus(mockTaskStatusModel).build());
        mockListModel.add(TaskModel.builder().taskId(1).taskName("Tarea Prueba 03").taskDescription("Tarea Prueba 03").taskStatus(mockTaskStatusModel).build());

        mockDTO = new Task(DEFAULT_TASK_NAME, 1).taskId(1L).taskDescription("Tarea Prueba 01");
        mockListDTO = new ArrayList<>();
        mockListDTO.add(new Task("Tarea Prueba 01", 1).taskId(1L).taskDescription("Tarea Prueba 01"));
        mockListDTO.add(new Task("Tarea Prueba 02", 1).taskId(1L).taskDescription("Tarea Prueba 02"));
        mockListDTO.add(new Task("Tarea Prueba 03", 1).taskId(1L).taskDescription("Tarea Prueba 03"));

        mockInputDTO = new TaskInput(DEFAULT_TASK_NAME,1).taskDescription("Tarea Prueba 01");
    }

    @Test
    void getAllTasks_empty() {
        Mockito.when(taskRepository.findAll()).thenReturn(new ArrayList<>(Collections.emptyList()));

        List<Task> list = service.getAllTasks();

        Assertions.assertThat(list).isEmpty();
    }

    @Test
    void getAllTasks_with_data() {
        Mockito.when(taskRepository.findAll()).thenReturn(mockListModel);

        List<Task> list = service.getAllTasks();

        Assertions.assertThat(list).isNotEmpty();
    }

    @Test
    void updateTask() {
        when(taskRepository.findById(1)).thenReturn(Optional.of(mockTaskModel));
        when(taskStatusRepository.findById(1)).thenReturn(Optional.of(mockTaskStatusModel));
        when(taskRepository.saveAndFlush(any(TaskModel.class))).thenReturn(mockTaskModel);

        // Act
        Task result = service.updateTask(mockInputDTO, 1);

        // Assert
        assertNotNull(result);
        assertEquals(mockTaskModel.getTaskName(), result.getTaskName());
        assertEquals(mockTaskModel.getTaskDescription(), result.getTaskDescription().get());
        verify(taskRepository).saveAndFlush(any(TaskModel.class));

    }

    @Test
    void newTask() {
        when(taskStatusRepository.findById(any(Integer.class))).thenReturn(Optional.of(mockTaskStatusModel));
        when(taskRepository.saveAndFlush(any(TaskModel.class))).thenReturn(mockTaskModel);

        Task result = service.newTask(mockInputDTO);

        assertNotNull(result);
        assertEquals(mockTaskModel.getTaskName(), result.getTaskName());
        assertEquals(mockTaskModel.getTaskDescription(), result.getTaskDescription().get());
    }

    @Test
    void getTaskById() {
        // Arrange
        when(taskRepository.findById(1)).thenReturn(Optional.of(mockTaskModel));

        // Act
        Task result = service.getTask(1);

        // Assert
        assertNotNull(result);
        assertEquals(Long.parseLong(mockTaskModel.getTaskId().toString()), result.getTaskId());
        assertEquals(mockTaskModel.getTaskName(), result.getTaskName());
    }

    @Test
    void getTaskByName() {
        // Arrange
        when(taskRepository.findByTaskName(DEFAULT_TASK_NAME)).thenReturn(Optional.of(mockTaskModel));

        // Act
        Task result = service.getTask(DEFAULT_TASK_NAME);

        // Assert
        assertNotNull(result);
        assertEquals(Long.parseLong(mockTaskModel.getTaskId().toString()), result.getTaskId());
        assertEquals(mockTaskModel.getTaskName(), result.getTaskName());
    }

    @Test
    void deleteTask() {
        // Arrange
        when(taskRepository.findById(1)).thenReturn(Optional.of(mockTaskModel));

        // Act
        Boolean result = service.deleteTask(1);

        // Assert
        assertTrue(result);
        verify(taskRepository).deleteById(1);
    }

    @Test
    void testDeleteTask() {
        // Arrange
        when(taskRepository.findByTaskName(DEFAULT_TASK_NAME)).thenReturn(Optional.of(mockTaskModel));

        // Act
        Boolean result = service.deleteTask(DEFAULT_TASK_NAME);

        // Assert
        assertTrue(result);
        verify(taskRepository).deleteByTaskName(DEFAULT_TASK_NAME);
    }
}