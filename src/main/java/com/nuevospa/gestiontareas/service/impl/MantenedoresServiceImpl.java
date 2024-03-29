package com.nuevospa.gestiontareas.service.impl;

import com.nuevospa.gestiontareas.data.EstadosTareasRepository;
import com.nuevospa.gestiontareas.dto.tareas.EstadoTareaDTO;
import com.nuevospa.gestiontareas.mapper.EstadoTareaMapper;
import com.nuevospa.gestiontareas.model.tareas.EstadoTarea;
import com.nuevospa.gestiontareas.service.MantenedoresService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MantenedoresServiceImpl implements MantenedoresService {
    private final EstadosTareasRepository estadosTareasRepository;

    public MantenedoresServiceImpl(EstadosTareasRepository estadosTareasRepository) {
        this.estadosTareasRepository = estadosTareasRepository;
    }

    @Override
    public Set<EstadoTareaDTO> obtenerTodosLosEstadosTareas() {
        Set<EstadoTarea> estados = new HashSet<>(estadosTareasRepository.findAll());
        return estados.stream()
                .map(EstadoTareaMapper::entityToDto)
                .collect(Collectors.toSet());
    }
}
