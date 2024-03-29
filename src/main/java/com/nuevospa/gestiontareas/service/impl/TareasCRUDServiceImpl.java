package com.nuevospa.gestiontareas.service.impl;

import com.nuevospa.gestiontareas.data.TareaRepository;
import com.nuevospa.gestiontareas.dto.tareas.TareaDTO;
import com.nuevospa.gestiontareas.exception.NotFoundException;
import com.nuevospa.gestiontareas.mapper.TareaMapper;
import com.nuevospa.gestiontareas.model.tareas.Tarea;
import com.nuevospa.gestiontareas.service.TareasCRUDService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TareasCRUDServiceImpl implements TareasCRUDService {
    private final TareaRepository tareaRepository;

    public TareasCRUDServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public TareaDTO crearTarea(TareaDTO tareaDTO) {
        Tarea tarea = TareaMapper.dtoToEntity(tareaDTO);
        tarea = tareaRepository.save(tarea);
        return TareaMapper.entityToDto(tarea);
    }

    @Override
    public TareaDTO actualizarTarea(TareaDTO tareaDTO) {
        Tarea tarea = TareaMapper.dtoToEntity(tareaDTO);
        tarea = tareaRepository.save(tarea);
        return TareaMapper.entityToDto(tarea);
    }

    @Override
    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    @Override
    public TareaDTO obtenerTareaPorId(Long id) throws NotFoundException {
        Tarea tarea = tareaRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
        return TareaMapper.entityToDto(tarea);
    }

    @Override
    public Set<TareaDTO> obtenerTodasLasTareas() {
        Set<Tarea> tareas = new HashSet<>(tareaRepository.findAll());
        return tareas.stream().map(TareaMapper::entityToDto).collect(Collectors.toSet());
    }
}
