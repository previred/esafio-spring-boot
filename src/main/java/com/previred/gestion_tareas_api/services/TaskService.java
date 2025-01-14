package com.previred.gestion_tareas_api.services;

import java.util.List;
import java.util.Optional;

import com.previred.gestion_tareas_api.dtos.TaskDTO;

public interface TaskService {

    List<TaskDTO> findAll();
    
    Optional<TaskDTO> findById(Long id);

    void save(TaskDTO taskDTOTaskDTO);
    
    Optional<TaskDTO> update(Long id, TaskDTO taskDTO );
    
    void deleteById(Long id);

}
