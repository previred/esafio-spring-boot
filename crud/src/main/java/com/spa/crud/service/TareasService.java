package com.spa.crud.service;

import com.spa.crud.dto.TareasDTO;
import com.spa.crud.model.Tareas;

import java.util.List;

public interface TareasService {
    String saveTask(Tareas tasks) throws Exception;
    String updateTask(Tareas task) throws Exception;
    List<TareasDTO> getAllTasks() throws Exception;
    TareasDTO findTaskById(Long id) throws Exception;
    void deleteTaskByNumeroTarea(String numeroTarea) throws Exception;
}
