package com.previred.desafiobackend.domain.services.task;

import com.previred.desafiobackend.data.entities.Task;
import com.previred.desafiobackend.data.entities.TaskStatus;
import com.previred.desafiobackend.data.entities.User;
import com.previred.desafiobackend.data.repository.task.TaskRepository;
import com.previred.desafiobackend.domain.dto.task.request.CreateTask;
import com.previred.desafiobackend.domain.dto.task.response.GetTask;
import com.previred.desafiobackend.domain.exception.task.TaskNotFoundException;
import com.previred.desafiobackend.domain.exception.user.UserNotFoundException;
import com.previred.desafiobackend.domain.services.task.status.StatusService;
import com.previred.desafiobackend.domain.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private StatusService statusService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setup() throws ParseException {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Should return a valid Task Object when a valid taskId is received.")
    @Test
    void getTask_useCase1() {
        //Arrange
        Task foundTask = Task.builder()
                .title("Test Task")
                .description("Test Task Description")
                .taskStatus(TaskStatus.builder().status("PENDING").build())
                .id(1L)
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .users(User.builder().email("test@previred.cl").role("ADMIN").build())
                .build();
        Mockito.when(taskRepository.findById(1L))
                .thenReturn(Optional.of(foundTask));

        //Act
        GetTask foundTaskDto = taskService.getTask(1L);

        //Assert
        Assertions.assertNotNull(foundTaskDto);
        Mockito.verify(taskRepository, Mockito.atLeastOnce()).findById(1L);
    }

    @DisplayName("Should throw NotFoundException when a non valid taskId is received.")
    @Test
    void getTask_useCase2() {
        //Arrange
        Mockito.when(taskRepository.findById(2L)).thenReturn(Optional.empty());

        //Act
        TaskNotFoundException exception = Assertions.assertThrows(TaskNotFoundException.class, () -> {
            taskService.getTask(2L);
        });

        //Assert
        Assertions.assertEquals("Task Not Found.", exception.getMessage());
    }

    @DisplayName("Should return a List of Tasks when getAllTask is called")
    @Test
    void getAllTask_useCase1() {
        //Arrange
        Task foundTask = Task.builder()
                .title("Test Task")
                .description("Test Task Description")
                .taskStatus(TaskStatus.builder().status("PENDING").build())
                .id(1L)
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .users(User.builder().email("test@previred.cl").role("ADMIN").build())
                .build();
        Mockito.when(taskRepository.findAll()).thenReturn(Collections.singletonList(foundTask));

        //Act
        List<GetTask> taskList = taskService.getAllTasks();

        //Assert
        Assertions.assertNotNull(taskList);
        Assertions.assertFalse(taskList.isEmpty());
    }

    @DisplayName("Should return a List of Tasks when getAllTask is called")
    @Test
    void getAllTask_useCase2() {
        //Arrange
        Mockito.when(taskRepository.findAll()).thenReturn(Collections.emptyList());

        //Act
        List<GetTask> taskList = taskService.getAllTasks();

        //Assert
        Assertions.assertNotNull(taskList);
        Assertions.assertTrue(taskList.isEmpty());
    }

    @DisplayName("Should execute at least once a save operation to create a new Task.")
    @Test
    void createTask_useCase1() {
        //Arrange
        User foundUser = User.builder()
                .name("Miguel")
                .lastName("San Juan")
                .dni("12345678-9")
                .role("ADMIN")
                .email("test@previred.cl")
                .build();
        Mockito.when(userService.findByEmail("test@previred.cl")).thenReturn(foundUser);

        TaskStatus pendingStatus = TaskStatus.builder().status("PENDING").build();
        Mockito.when(statusService.getStatus("PENDING")).thenReturn(pendingStatus);

        CreateTask createTaskRequest = CreateTask.builder()
                .asignedUserEmail("test@previred.cl")
                .description("Test Description")
                .title("Test Title")
                .build();

        //Act
        taskService.create(createTaskRequest);

        //Assert
        Mockito.verify(userService, Mockito.times(1)).findByEmail("test@previred.cl");
        Mockito.verify(taskRepository, Mockito.times(1)).save(Mockito.any());
    }

    @DisplayName("Should throw UserNotFoundException when createTask is called with a non valid user identifier.")
    @Test
    void createTask_useCase2() {
        //Arrange
        Mockito.when(userService.findByEmail("nonvalid@previred.cl")).thenThrow(new UserNotFoundException("User Not Found."));

        CreateTask createTaskRequest = CreateTask.builder()
                .asignedUserEmail("nonvalid@previred.cl")
                .description("Test Description")
                .title("Test Title")
                .build();

        //Act
        UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class, () -> {
            taskService.create(createTaskRequest);
        });

        //Assert
        Assertions.assertEquals("User Not Found.", exception.getMessage());
    }

    @DisplayName("Should throw JPAException when createTask is called.")
    @Test
    void createTask_useCase3() {
        //Arrange

        //Act

        //Assert
    }

}
