package com.previred.desafioApiRest.desafioprevired.service.impl;

import com.previred.desafioApiRest.desafioprevired.repository.EstadoTareaRepository;
import com.previred.desafioApiRest.desafioprevired.repository.model.EstadoTarea;
import com.previred.desafioApiRest.desafioprevired.service.EstadoTareaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoTareaServiceImpl implements EstadoTareaService {

    private final EstadoTareaRepository estadoTareaRepository;

    public EstadoTareaServiceImpl(EstadoTareaRepository estadoTareaRepository) {
        this.estadoTareaRepository = estadoTareaRepository;
    }


    @Override
    public EstadoTarea create(EstadoTarea estadoTarea) {
        return estadoTareaRepository.save(estadoTarea);
    }

    @Override
    public EstadoTarea findById(Long id) {
        Optional<EstadoTarea> optionalEstadoTarea = estadoTareaRepository.findById(id);
        return optionalEstadoTarea.get();
    }

    @Override
    public List<EstadoTarea> obtenerEstadoTare() {
        return estadoTareaRepository.findAll();
    }
}
