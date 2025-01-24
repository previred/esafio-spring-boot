package com.previred.desafio.mapper;

import com.previred.desafio.dto.TareaDto;
import com.previred.desafio.model.EstadoTarea;
import com.previred.desafio.model.Tarea;
import com.previred.desafio.repository.EstadoTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TareaMapper.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

@Component
public class TareaMapper {

    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    public TareaDto toDto(Tarea tarea) {
        if (tarea == null) {
            return null;
        }
        TareaDto tareaDTO = new TareaDto();
        tareaDTO.setId(tarea.getId());
        tareaDTO.setDescription(tarea.getDescription());
        tareaDTO.setStatusId(tarea.getStatus().getId());
        return tareaDTO;
    }

    public Tarea toEntity(TareaDto tareaDTO) {
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