package com.nuevospa.gestiontareas.mapper;

import com.nuevospa.gestiontareas.dto.tareas.EstadoTareaDTO;
import com.nuevospa.gestiontareas.model.tareas.EstadoTarea;

public class EstadoTareaMapper {
    private EstadoTareaMapper() {
        //
    }

    public static EstadoTarea dtoToEntity(EstadoTareaDTO estadoTareaDTO) {
        if (estadoTareaDTO == null) {
            return null;
        }

        return EstadoTarea.builder()
                .id(estadoTareaDTO.getId())
                .nombre(estadoTareaDTO.getNombre())
                .build();
    }

    public static EstadoTareaDTO entityToDto(EstadoTarea estadoTarea) {
        if (estadoTarea == null) {
            return null;
        }

        return EstadoTareaDTO.builder()
                .id(estadoTarea.getId())
                .nombre(estadoTarea.getNombre())
                .build();
    }
}
