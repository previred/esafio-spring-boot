package com.previred.api.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.api.dtos.EstadosTareaDTO;
import com.previred.api.models.EstadosTarea;
import com.previred.api.repositories.EstadosTareaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadosTareaService {

    private final EstadosTareaRepository estadosTareaRepository;
    private final ObjectMapper objectMapper;

    public EstadosTareaDTO buscarPorId(Long id) {
        Optional<EstadosTarea> estadoTarea = estadosTareaRepository.findById(id);
        if (estadoTarea.isPresent()) {
            return objectMapper.convertValue(estadoTarea.get(), EstadosTareaDTO.class);
        }
        return null;
    }

}
