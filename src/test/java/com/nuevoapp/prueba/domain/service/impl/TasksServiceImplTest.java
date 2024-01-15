package com.nuevoapp.prueba.domain.service.impl;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.nuevoapp.prueba.domain.model.dto.TasksDto;
import com.nuevoapp.prueba.domain.model.entity.TasksEntity;
import com.nuevoapp.prueba.domain.model.mappers.TasksMapper;
import com.nuevoapp.prueba.domain.repository.TasksRepository;
import com.nuevoapp.prueba.utils.PatchUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TasksServiceImplTest {

    @Mock
    private TasksRepository repository;

    @Mock
    private TasksMapper mapper;

    @Mock
    private PatchUtils patchUtils;

    @InjectMocks
    private TasksServiceImpl tasksService;

    @Test
    void testGetTaskById_Success() {
        int taskId = 1;
        TasksEntity tasksEntity = new TasksEntity();
        TasksDto tasksDto = new TasksDto();

        when(repository.findById(taskId))
                .thenReturn(Optional.of(tasksEntity));

        when(mapper.toDto(tasksEntity))
                .thenReturn(tasksDto);

        TasksDto result = tasksService.getTaskById(taskId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(tasksDto, result);
    }

    @Test
    void testGetTaskById_TaskNotFound() {

        int taskId = 1;

        when(repository.findById(taskId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> tasksService.getTaskById(taskId));
    }

    @Test
    void testCreateTask_Success() {
        // Mock data
        TasksDto tasksDto = new TasksDto();
        TasksEntity tasksEntity = new TasksEntity();

        when(repository.save(any(TasksEntity.class)))
                .thenReturn(tasksEntity);

        when(mapper.updateTaskFromDto(eq(tasksDto), any(TasksEntity.class)))
                .thenReturn(tasksEntity);

        when(mapper.toDto(tasksEntity))
                .thenReturn(tasksDto);

        TasksDto result = tasksService.createTask(tasksDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(tasksDto, result);
    }

    @Test
    void testCreateTaskBatch_Success() {
        List<TasksDto> tasksDtoList = List.of(new TasksDto(), new TasksDto());
        List<TasksEntity> tasksEntityList = List.of(new TasksEntity(), new TasksEntity());

        when(repository.saveAll(anyCollection()))
                .thenReturn(tasksEntityList);

        when(mapper.updateTaskFromDto(any(TasksDto.class), any(TasksEntity.class)))
                .thenReturn(new TasksEntity());

        when(mapper.toDto(any(TasksEntity.class)))
                .thenReturn(new TasksDto());

        List<TasksDto> result = tasksService.createTaskBatch(tasksDtoList);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(tasksDtoList.size(), result.size());
    }

    @Test
    void testGetTaskByEmail_Success() {
        // Mock data
        String userEmail = "test@example.com";
        List<TasksEntity> tasksEntityList = Collections.singletonList(new TasksEntity());
        List<TasksDto> tasksDtoList = Collections.singletonList(new TasksDto());

        // Mock the behavior of repository.findAllByUserEmail
        when(repository.findAllByUserEmail(userEmail))
                .thenReturn(tasksEntityList);

        // Mock the behavior of mapper.toDto
        when(mapper.toDto(any(TasksEntity.class)))
                .thenReturn(new TasksDto());

        // Perform the actual test
        List<TasksDto> result = tasksService.getTaskByEmail(userEmail);

        // Assert the expected result
        assertNotNull(result);
        assertEquals(tasksDtoList.size(), result.size());
    }

    @Test
    void testGetTaskByEmail_NoTasksFound() {
        // Mock data
        String userEmail = "nonexistent@example.com";

        // Mock the behavior of repository.findAllByUserEmail
        when(repository.findAllByUserEmail(userEmail))
                .thenReturn(Collections.emptyList());

        // Perform the actual test and assert NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> tasksService.getTaskByEmail(userEmail));
    }

    @Test
    void testUpdateTaskById_Success() {
        TasksDto tasksDto = new TasksDto();
        tasksDto.setId(1);
        TasksEntity tasksEntity = new TasksEntity();

        when(repository.findById(anyInt()))
                .thenReturn(Optional.of(new TasksEntity()));

        when(repository.save(any(TasksEntity.class)))
                .thenReturn(tasksEntity);

        when(mapper.toEntity(any(TasksDto.class)))
                .thenReturn(new TasksEntity());

        when(mapper.toDto(any(TasksEntity.class)))
                .thenReturn(tasksDto);

        TasksDto result = tasksService.updateTaskById(tasksDto);


        assertNotNull(result);
        assertEquals(tasksDto, result);
    }

    @Test
    void testUpdateTaskById_IdIsNull() {

        TasksDto tasksDto = new TasksDto();
        tasksDto.setId(null);

        assertThrows(IllegalArgumentException.class, () -> tasksService.updateTaskById(tasksDto));
    }

    @Test
    void testUpdateTaskById_TaskNotFound() {
        TasksDto tasksDto = new TasksDto();
        tasksDto.setId(1);

        when(repository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> tasksService.updateTaskById(tasksDto));
    }

    @Test
    void testPatchTaskById_Success() {
        int taskId = 1;
        JsonPatch patchOperations =  new JsonPatch(List.of(new JsonPatchOperation[]{}));
        TasksDto tasksDto = new TasksDto();
        TasksEntity tasksEntity = new TasksEntity();

        when(repository.findById(taskId))
                .thenReturn(Optional.of(tasksEntity));

        when(patchUtils.applyPatch(tasksDto, patchOperations))
                .thenReturn(tasksDto);

        when(repository.save(tasksEntity))
                .thenReturn(tasksEntity);

        when(mapper.toEntity(tasksDto))
                .thenReturn(tasksEntity);
        when(mapper.toDto(tasksEntity))
                .thenReturn(tasksDto);

        TasksDto result = tasksService.patchTaskById(taskId, patchOperations);

        assertNotNull(result);
        assertEquals(tasksDto, result);
    }

    @Test
    void testPatchTaskById_TaskNotFound() {
        // Mock data
        int taskId = 1;
        JsonPatch patchOperations =  new JsonPatch(List.of(new JsonPatchOperation[]{}));

        // Mock the behavior of repository.findById
        when(repository.findById(taskId))
                .thenReturn(Optional.empty());

        // Perform the actual test and assert NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> tasksService.patchTaskById(taskId, patchOperations));
    }

    @Test
    void testDeleteTaskById_Success() {
        int taskId = 1;

        when(repository.findById(taskId))
                .thenReturn(Optional.of(new TasksEntity()))
                .thenReturn(Optional.empty());

        boolean result = tasksService.deleteTaskById(taskId);

        assertTrue(result);
    }

    @Test
    void testDeleteTaskById_TaskNotFound() {
        int taskId = 1;

        when(repository.findById(taskId))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> tasksService.deleteTaskById(taskId));

    }
}
