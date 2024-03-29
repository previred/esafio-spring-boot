package com.nuevospa.gestiontareas.service;

import com.nuevospa.gestiontareas.dto.tareas.TareaDTO;
import com.nuevospa.gestiontareas.exception.NotFoundException;

import java.util.Set;

public interface TareasCRUDService {
    TareaDTO crearTarea(TareaDTO tareaDTO);

    TareaDTO actualizarTarea(TareaDTO tareaDTO);

    void eliminarTarea(Long id);

    TareaDTO obtenerTareaPorId(Long id) throws NotFoundException;

    Set<TareaDTO> obtenerTodasLasTareas();
}
