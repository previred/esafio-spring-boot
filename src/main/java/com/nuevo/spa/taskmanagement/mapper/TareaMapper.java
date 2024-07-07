package com.nuevo.spa.taskmanagement.mapper;

import com.nuevo.spa.taskmanagement.dto.TareaDTO;
import com.nuevo.spa.taskmanagement.model.EstadoTarea;
import com.nuevo.spa.taskmanagement.model.Tarea;
import com.nuevo.spa.taskmanagement.repository.EstadoTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TareaMapper {

    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    public TareaDTO toDto(Tarea tarea) {
        if (tarea == null) {
            return null;
        }
        TareaDTO tareaDTO = new TareaDTO();
        tareaDTO.setId(tarea.getId());
        tareaDTO.setDescription(tarea.getDescription());
        tareaDTO.setStatusId(tarea.getStatus().getId());
        return tareaDTO;
    }

    public Tarea toEntity(TareaDTO tareaDTO) {
        if (tareaDTO == null) {
            return null;
        }
        Tarea tarea = new Tarea();
        tarea.setId(tareaDTO.getId());
        tarea.setDescription(tareaDTO.getDescription());
        EstadoTarea status = estadoTareaRepository.findById(tareaDTO.getStatusId())
                .orElseThrow(() -> new RuntimeException("Estado de tarea no encontrado"));
        tarea.setStatus(status);
        return tarea;
    }
}
