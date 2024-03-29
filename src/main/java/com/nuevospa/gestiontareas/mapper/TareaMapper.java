package com.nuevospa.gestiontareas.mapper;

import com.nuevospa.gestiontareas.dto.tareas.TareaDTO;
import com.nuevospa.gestiontareas.model.tareas.Tarea;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TareaMapper {
    private TareaMapper() {
        //
    }

    public static Tarea dtoToEntity(TareaDTO tareaDTO) {
        if (tareaDTO == null) {
            return null;
        }

        return Tarea.builder()
                .id(tareaDTO.getId())
                .descripcion(tareaDTO.getDescripcion())
                .usuario(UsuarioMapper.dtoToEntity(tareaDTO.getUsuario()))
                .estadoTarea(EstadoTareaMapper.dtoToEntity(tareaDTO.getEstadoTarea()))
                .build();
    }

    public static Set<Tarea> dtoToEntity(Set<TareaDTO> tareas) {
        if (tareas == null) {
            return new HashSet<>();
        } else {
            return tareas.stream()
                    .map(TareaMapper::dtoToEntity)
                    .collect(Collectors.toSet());
        }
    }

    public static TareaDTO entityToDto(Tarea tarea) {
        if (tarea == null) {
            return null;
        }

        return TareaDTO.builder()
                .id(tarea.getId())
                .descripcion(tarea.getDescripcion())
                .usuario(UsuarioMapper.entityToDto(tarea.getUsuario()))
                .estadoTarea(EstadoTareaMapper.entityToDto(tarea.getEstadoTarea()))
                .build();
    }

    public static Set<TareaDTO> entityToDto(Set<Tarea> tareas) {
        if (tareas == null) {
            return new HashSet<>();
        } else {
            return tareas.stream()
                    .map(TareaMapper::entityToDto)
                    .collect(Collectors.toSet());
        }
    }
}
