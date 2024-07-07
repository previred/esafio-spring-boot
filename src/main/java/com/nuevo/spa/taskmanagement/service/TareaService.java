package com.nuevo.spa.taskmanagement.service;

import com.nuevo.spa.taskmanagement.dto.TareaDTO;
import java.util.List;

public interface TareaService {
    List<TareaDTO> getAllTareas();
    TareaDTO getTareaById(Long id);
    TareaDTO createTarea(TareaDTO tareaDTO);
    TareaDTO updateTarea(Long id, TareaDTO tareaDTO);
    boolean deleteTarea(Long id);
}
