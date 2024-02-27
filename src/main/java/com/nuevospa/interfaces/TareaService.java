package com.nuevospa.interfaces;

import java.util.List;
import java.util.Optional;

import com.nuevospa.dto.TareaDTO;
import com.nuevospa.dto.TareaIdDTO;
import com.nuevospa.entity.EstadoTareaEntity;

public interface TareaService {

    List<TareaDTO> obtenerTareas();

    Optional<TareaDTO> obtenerTareaPorId(Long id);

    void eliminarTareaPorId(Long id);
    
    TareaDTO guardarTarea(TareaDTO tareaDTO, EstadoTareaEntity estadoTarea);
    
	Optional<TareaDTO> actualizarTarea(TareaIdDTO tareaDTO, EstadoTareaEntity estadoTarea);
}
