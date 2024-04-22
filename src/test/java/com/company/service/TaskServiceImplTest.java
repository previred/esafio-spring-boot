package com.company.service;

import com.company.exception.AppException;
import com.company.exception.enums.CodeExceptions;
import com.company.model.Task;
import com.company.persistence.task.TaskDataProvider;
import com.company.service.task.TaskServiceImpl;
import com.company.service.task.status.TaskStatusService;
import com.company.service.users.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;


public class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private  TaskDataProvider taskDataProvider;

    @Mock
    private  TaskStatusService taskStatusService;

    @Mock
    private  UserService userService;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        task = Task.builder()
                .uuid(UUID.randomUUID())
                .taskStatusId(UUID.randomUUID())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .userId(UUID.randomUUID())
                .description("Tarea de prueba")
                .build();
    }


    @Test
    public void createTask(){
        taskService.create(task);
        Mockito.verify(taskDataProvider).create(Mockito.any());
    }


    @Test
    public void updateTask(){
        mockTaskFindById(Optional.of(task));
        taskService.update(task);
        Mockito.verify(taskDataProvider).create(Mockito.any());
    }

    @Test
    public void deleteTask(){
        mockTaskFindById(Optional.of(task));
        taskService.delete(UUID.randomUUID());
        Mockito.verify(taskDataProvider).delete(Mockito.any());
    }


    @Test
    public void findTaskById() {
        mockTaskFindById(Optional.of(task));
        Optional<Task> optionalTask = taskService.findById(UUID.randomUUID());
        Assertions.assertTrue(optionalTask.isPresent());
    }

    @Test
    public void getAllTask() {
        Mockito.when(taskDataProvider.getAll()).thenReturn(Collections.singletonList(task));
        Assertions.assertNotNull(taskService.getAll());
    }


    @Test
    public void createTask_validTaskStatus(){
        throwTaskStatusNotFound();
        validAppExceptionCreate(CodeExceptions.TASK_STATUS_NOT_FOUND);
    }

    @Test
    public void createTask_validUser(){
        throwUserNotFound();
        validAppExceptionCreate(CodeExceptions.USER_NOT_FOUND);
    }


    @Test
    public void updateTask_validTask(){
        mockTaskFindById(Optional.empty());
        validAppExceptionUpdate(CodeExceptions.TASK_NOT_FOUND);
    }

    @Test
    public void updateTask_validTaskStatus(){
        mockTaskFindById(Optional.of(task));
        throwTaskStatusNotFound();
        validAppExceptionUpdate(CodeExceptions.TASK_STATUS_NOT_FOUND);
    }

    @Test
    public void updateTask_validUser(){
        mockTaskFindById(Optional.of(task));
        throwUserNotFound();
        validAppExceptionUpdate(CodeExceptions.USER_NOT_FOUND);
    }

    @Test
    public void deleteTask_validTask(){
        mockTaskFindById(Optional.empty());
        validAppExceptionDelete(CodeExceptions.TASK_NOT_FOUND);
    }


    private void mockTaskFindById(Optional<Task> task) {
        Mockito.when(taskDataProvider.findById(Mockito.any()))
                .thenReturn(task);
    }

    private void throwTaskStatusNotFound() {
        Mockito.doThrow(new AppException(CodeExceptions.TASK_STATUS_NOT_FOUND))
               .when(taskStatusService).validTaskStatus(Mockito.any());
    }

    private void throwUserNotFound() {
        Mockito.doThrow(new AppException(CodeExceptions.USER_NOT_FOUND))
                .when(userService).validUserId(Mockito.any());
    }

    private void validAppExceptionCreate(CodeExceptions exceptionExpected) {
        try {
           taskService.create(task);
        } catch (AppException appException){
            Assertions.assertEquals(exceptionExpected.getValue(), appException.getCodError());
            return;
        }
        Assertions.fail();
    }

    private void validAppExceptionUpdate(CodeExceptions exceptionExpected) {
        try {
            taskService.update(task);
        } catch (AppException appException){
            Assertions.assertEquals(exceptionExpected.getValue(), appException.getCodError());
            return;
        }
        Assertions.fail();
    }

    private void validAppExceptionDelete(CodeExceptions exceptionExpected) {
        try {
            taskService.delete(UUID.randomUUID());
        } catch (AppException appException){
            Assertions.assertEquals(exceptionExpected.getValue(), appException.getCodError());
            return;
        }
        Assertions.fail();
    }



}
