package com.enterprise.move.apps.MoveApps;

import com.enterprise.move.apps.MoveApps.controller.TaskController;
import com.enterprise.move.apps.MoveApps.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.Task;
import org.openapitools.model.TaskSuccessful;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;


    @Test
    @DisplayName("Return Task successful")
    void returnAllTasksSuccessful() {

        List<Task> mockTasks = new ArrayList<>();
        mockTasks.add(new Task().idTask("1"));
        mockTasks.add(new Task().idTask("2"));
        when(taskService.getTask()).thenReturn(mockTasks);

        ResponseEntity<List<Task>> response = taskController.getTask();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockTasks, response.getBody());
        verify(taskService, times(1)).getTask();
    }

    @Test
    @DisplayName("Create Task Successful")
    void createTaskSuccessful() {

        Task mockTask = new Task().idTask("1");
        TaskSuccessful mockResponse = new TaskSuccessful().code("0").message("Create Successful");
        when(taskService.createTask(any(Task.class))).thenReturn(mockResponse);

        ResponseEntity<TaskSuccessful> response = taskController.saveTask(mockTask);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(taskService, times(1)).createTask(any(Task.class));
    }

    @Test
    @DisplayName("Update Task is Successful")
    void updateTaskSuccessful() {

        Task mockTask = new Task().idTask("1");
        TaskSuccessful mockResponse = new TaskSuccessful().code("0").message("Update Successful");
        when(taskService.updateTask(anyInt(), any(Task.class))).thenReturn(mockResponse);

        ResponseEntity<TaskSuccessful> response = taskController.taskIdPut(1, mockTask);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(taskService, times(1)).updateTask(anyInt(), any(Task.class));
    }

    @Test
    @DisplayName("Delete Task is Successful")
    void deleteTaskSuccessful() {

        ResponseEntity<Void> response = taskController.taskIdDelete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taskService, times(1)).deleteTask(1);
    }
}