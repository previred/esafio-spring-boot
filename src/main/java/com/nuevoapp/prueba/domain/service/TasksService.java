package com.nuevoapp.prueba.domain.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.nuevoapp.prueba.domain.model.dto.TasksDto;

import java.util.List;

public interface TasksService {
    TasksDto getTaskById(Integer id);
    List<TasksDto> getTaskByEmail(String email);
    TasksDto createTask(TasksDto dto);
    List<TasksDto> createTaskBatch(List<TasksDto> list);

    TasksDto updateTaskById(TasksDto dto);

    TasksDto patchTaskById(Integer id, JsonPatch patchOperations);

    Boolean deleteTaskById(Integer id);
}
