package com.nuevo.spa.taskmanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuevo.spa.taskmanagement.constants.Messages;
import com.nuevo.spa.taskmanagement.dto.TareaDTO;
import com.nuevo.spa.taskmanagement.mapper.TareaMapper;
import com.nuevo.spa.taskmanagement.model.EstadoTarea;
import com.nuevo.spa.taskmanagement.model.Tarea;
import com.nuevo.spa.taskmanagement.repository.EstadoTareaRepository;
import com.nuevo.spa.taskmanagement.repository.TareaRepository;
import com.nuevo.spa.taskmanagement.service.TareaService;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    @Autowired
    private TareaMapper tareaMapper;

    @Override
    public List<TareaDTO> getAllTareas() {
        List<Tarea> tareas = tareaRepository.findAll();
        return tareas.stream().map(tareaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TareaDTO getTareaById(Long id) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        return tareaMapper.toDto(tarea);
    }

    @Override
    public TareaDTO createTarea(TareaDTO tareaDTO) {
        Tarea tarea = tareaMapper.toEntity(tareaDTO);
        Tarea savedTarea = tareaRepository.save(tarea);
        return tareaMapper.toDto(savedTarea);
    }

    @Override
    public TareaDTO updateTarea(Long id, TareaDTO tareaDTO) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        if (tarea != null) {
            tarea.setDescription(tareaDTO.getDescription());
            EstadoTarea status = estadoTareaRepository.findById(tareaDTO.getStatusId())
                    .orElseThrow(() -> new RuntimeException(Messages.TASK_STATUS_NOT_FOUND));
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
