package com.enterprise.move.apps.MoveApps;

import com.enterprise.move.apps.MoveApps.model.StatusTask;
import com.enterprise.move.apps.MoveApps.model.TaskModel;
import com.enterprise.move.apps.MoveApps.model.User;
import com.enterprise.move.apps.MoveApps.process.TaskProcessor;
import com.enterprise.move.apps.MoveApps.repository.StatusTaskRepository;
import com.enterprise.move.apps.MoveApps.repository.TaskRepository;
import com.enterprise.move.apps.MoveApps.repository.UserRepository;
import com.enterprise.move.apps.MoveApps.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.Task;
import org.openapitools.model.TaskSuccessful;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private  TaskRepository taskRepository;

    @Mock
    private  TaskProcessor taskProcessor;

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  StatusTaskRepository statusTaskRepository;

    @InjectMocks
    private TaskService taskService;


    private  List<TaskModel> taskModelList;
    private List<Task> taskList;

    private User user;
    private StatusTask statusTask;

    private Task task;
    private TaskModel taskModel;
    @BeforeEach
    void setUp() {

        task = new Task();
        task.setResponsible("John Doe");
        task.setStatusTask("In Progress");

        user = new User();
        user.setName("John Doe");

        taskModel = new TaskModel();
        taskModel.setId(1L);

        statusTask = new StatusTask();
        statusTask.setNameStatusTask("In Progress");

        taskModelList = List.of(new TaskModel("Code in Python Ssr","Framework Django","ALTA"), new TaskModel("Code Python Adv","Framework Django","MEDIA"));
        taskList = List.of(new Task("Code in Python Ssr"), new Task("Code Python Adv"));
    }

    @Test
    @DisplayName("Return Task when exist data")
    void returnTaskWhenExistData() {

        when(taskRepository.findAll()).thenReturn(taskModelList);
        when(taskProcessor.buildTask(taskModelList)).thenReturn(taskList);


        List<Task> result = taskService.getTask();

        assertNotNull(result);
        assertEquals(taskList.size(), result.size());
        assertEquals(taskList, result);
        verify(taskRepository).findAll();
        verify(taskProcessor).buildTask(taskModelList);
    }

    @Test
    @DisplayName("Do not return anything Task")
    void returnFailWhenRepositoryReturnsNull() {

        when(taskRepository.findAll()).thenReturn(Collections.emptyList());
        when(taskProcessor.buildTask(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<Task> result = taskService.getTask();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(taskRepository).findAll();
        verify(taskProcessor).buildTask(Collections.emptyList());
    }


    @Test
    @DisplayName("Create Task successful")
    void createTaskWhenTaskIsValid() {

        when(userRepository.findByName("John Doe")).thenReturn(user);
        when(statusTaskRepository.findByNameStatusTask("In Progress")).thenReturn(statusTask);
        when(taskProcessor.convertTaskToTaskModel(task, user, statusTask)).thenReturn(taskModel);

        TaskSuccessful result = taskService.createTask(task);


        assertEquals("0", result.getCode());
        assertEquals("Create Successful", result.getMessage());
        verify(taskRepository).save(taskModel);
    }


    @Test
    @DisplayName("return error when Create Task because user is invalid ")
    void returnErrorWhenCreateTaskBecauseUserIsInvalid() {

        when(userRepository.findByName("John Doe")).thenReturn(null);

        TaskSuccessful result = taskService.createTask(task);

        assertEquals("-1", result.getCode());
        assertEquals("Name is invalid", result.getMessage());
        verify(taskRepository, never()).save(any(TaskModel.class));
    }


    @Test
    @DisplayName("return error when Create Task because task is invalid ")
    void returnErrorWhenCreateTaskBecauseStatusTaskIsInvalid() {

        when(userRepository.findByName("John Doe")).thenReturn(user);
        when(statusTaskRepository.findByNameStatusTask("In Progress")).thenReturn(null);

        TaskSuccessful result = taskService.createTask(task);

        assertEquals("-2", result.getCode());
        assertEquals("Status Task is invalid", result.getMessage());
        verify(taskRepository, never()).save(any(TaskModel.class));
    }

    @Test
    @DisplayName("Update Task is successful")
    void updateTaskWhenTaskIsValid() {

        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskModel));
        when(userRepository.findByName("John Doe")).thenReturn(user);
        when(statusTaskRepository.findByNameStatusTask("In Progress")).thenReturn(statusTask);
        when(taskProcessor.convertTaskToTaskModelUpdate(task, user, statusTask)).thenReturn(taskModel);


        TaskSuccessful result = taskService.updateTask(1, task);

        assertEquals("0", result.getCode());
        assertEquals("Update Successful", result.getMessage());
        verify(taskRepository).save(taskModel);
    }

    @Test
    @DisplayName("Update Task but return error because Task ID is invalid")
    void returnErrorWhenUpdateTaskBecauseTaskIdIsInvalid() {

        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        TaskSuccessful result = taskService.updateTask(1, task);

        assertEquals("-3", result.getCode());
        assertEquals("ID not exist", result.getMessage());
        verify(taskRepository, never()).save(any(TaskModel.class));
    }

    @Test
    @DisplayName("Delete Task When ID is valid")
    void deleteTaskWhenIdIsValid() {

        doNothing().when(taskRepository).deleteById(1L);
        taskService.deleteTask(1);
        verify(taskRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Return Error when Delete Task ")
    void returnErrorWhenDeleteTask() {

        doThrow(new RuntimeException("Deletion failed")).when(taskRepository).deleteById(1L);
        taskService.deleteTask(1);
        verify(taskRepository).deleteById(1L);

    }


}
