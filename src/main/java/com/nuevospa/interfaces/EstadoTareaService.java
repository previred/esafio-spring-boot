package com.nuevospa.interfaces;

import java.util.List;
import java.util.Optional;

import com.nuevospa.dto.EstadoTareaDTO;
import com.nuevospa.entity.EstadoTareaEntity;

public interface EstadoTareaService {

    EstadoTareaEntity guardarEstadoTarea(String nombre);

    List<EstadoTareaDTO> obtenerTodosEstadosTarea();

    EstadoTareaEntity obtenerEstadoTareaEntityPorId(Long id);
    
    Optional<Object> obtenerEstadoTareaPorId(Long id);

    void eliminarEstadoTareaPorId(Long id);

	Optional<EstadoTareaDTO> actualizarEstadoTarea(EstadoTareaDTO estadoTareaDTO);
    
}
