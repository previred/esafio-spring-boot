package com.nuevospa.gestiontareas.service;

import com.nuevospa.gestiontareas.dto.tareas.EstadoTareaDTO;

import java.util.Set;

public interface MantenedoresService {
    Set<EstadoTareaDTO> obtenerTodosLosEstadosTareas();
}
