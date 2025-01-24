package com.previred.desafio.service.impl;

import com.previred.desafio.dto.TareaDto;
import com.previred.desafio.mapper.TareaMapper;
import com.previred.desafio.model.EstadoTarea;
import com.previred.desafio.model.Tarea;
import com.previred.desafio.repository.EstadoTareaRepository;
import com.previred.desafio.repository.TareaRepository;
import com.previred.desafio.service.TareaService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TareaServiceImpl.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    @Autowired
    private TareaMapper tareaMapper;

    @Override
    public List<TareaDto> getAllTareas() {
        List<Tarea> tareas = tareaRepository.findAll();
        return tareas.stream().map(tareaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TareaDto getTareaById(Long id) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        return tareaMapper.toDto(tarea);
    }

    @Override
    public TareaDto createTarea(TareaDto tareaDTO) {
        Tarea tarea = tareaMapper.toEntity(tareaDTO);
        Tarea savedTarea = tareaRepository.save(tarea);
        return tareaMapper.toDto(savedTarea);
    }

    @Override
    public TareaDto updateTarea(Long id, TareaDto tareaDTO) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        if (tarea != null) {
            tarea.setDescription(tareaDTO.getDescription());
            EstadoTarea status = estadoTareaRepository.findById(tareaDTO.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Estado de tarea no encontrado"));
            tarea.setStatus(status);
            Tarea updatedTarea = tareaRepository.save(tarea);
            return tareaMapper.toDto(updatedTarea);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteTarea(Long id) {
        if (tareaRepository.existsById(id)) {
            tareaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
