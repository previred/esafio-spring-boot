package com.previred.challenge.services.impl;

import com.previred.challenge.dto.TaskRequestDTO;
import com.previred.challenge.exceptions.TaskNoAccessException;
import com.previred.challenge.mappers.TaskModelMapper;
import com.previred.challenge.model.TaskModel;
import com.previred.challenge.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.previred.challenge.enums.TaskStatus.RUNNING;
import static com.previred.challenge.enums.TaskStatus.TODO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl service;

    @Mock
    private TaskRepository taskRepository;

    @Spy
    private TaskModelMapper taskModelMapper = Mappers.getMapper(TaskModelMapper.class);

    @Captor
    private ArgumentCaptor<TaskModel> taskModelArgumentCaptor;

    @Test
    void givenSaveRequestWhenAllParamsOkThenShouldSaveEntity() {
        var savedTask = new TaskModel();
        savedTask.setId(1);
        savedTask.setName("task1");
        savedTask.setStatus(TODO);
        savedTask.setUserId(1);

        when(taskRepository.save(any())).thenReturn(savedTask);

        var request = new TaskRequestDTO("task1", "", TODO);
        var response = service.add(1, request);

        assertEquals("task1", response.name());
        assertEquals(TODO, response.status());

        verify(taskModelMapper).toModel(request);
    }

    @Test
    void givenUpdateRequestWhenAllParamsOkThenShouldUpdateEntity() {
        var updatedTask = new TaskModel();
        updatedTask.setId(1);
        updatedTask.setName("task1");
        updatedTask.setStatus(TODO);
        updatedTask.setUserId(1);

        var request = new TaskRequestDTO("task2", "new desc", RUNNING);
        when(taskRepository.findById(eq(1))).thenReturn(Optional.of(updatedTask));
        service.updateTask(1, 1, request);

        verify(taskRepository).save(taskModelArgumentCaptor.capture());
        var capturedTaskModel = taskModelArgumentCaptor.getValue();

        assertEquals("task2", capturedTaskModel.getName());
        assertEquals("new desc", capturedTaskModel.getDescription());
        assertEquals(RUNNING, capturedTaskModel.getStatus());
    }

    @Test
    void givenUpdateRequestWhenUserNotOwnerThenShouldThrowException() {
        var updatedTask = new TaskModel();
        updatedTask.setId(1);
        updatedTask.setName("task1");
        updatedTask.setStatus(TODO);
        updatedTask.setUserId(200);

        when(taskRepository.findById(eq(1))).thenReturn(Optional.of(updatedTask));
        var request = new TaskRequestDTO("task2", "new desc", RUNNING);

        assertThrows(TaskNoAccessException.class, () -> service.updateTask(1, 1, request));
    }

    @Test
    void givenDeleteRequestWhenAllOKThenShouldProceed() {
        var updatedTask = new TaskModel();
        updatedTask.setId(100);
        updatedTask.setUserId(1);

        when(taskRepository.findById(eq(100))).thenReturn(Optional.of(updatedTask));
        service.deleteTask(1,100);

        verify(taskRepository).delete(taskModelArgumentCaptor.capture());
        var deletedItem = taskModelArgumentCaptor.getValue();
        assertEquals(100, deletedItem.getId());
    }

    @Test
    void givenDeleteRequestWhenUserNotOwnerThenShouldFail() {
        var updatedTask = new TaskModel();
        updatedTask.setId(100);
        updatedTask.setUserId(2);

        when(taskRepository.findById(eq(100))).thenReturn(Optional.of(updatedTask));
        assertThrows(TaskNoAccessException.class, () -> service.deleteTask(1,100));
    }

}
