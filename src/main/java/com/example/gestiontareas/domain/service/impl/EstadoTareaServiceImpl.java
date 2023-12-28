package com.example.gestiontareas.domain.service.impl;

import com.example.gestiontareas.domain.model.EstadoTarea;
import com.example.gestiontareas.domain.repository.EstadoTareaRepository;
import com.example.gestiontareas.domain.service.IEstadoTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoTareaServiceImpl implements IEstadoTareaService {

    private final EstadoTareaRepository estadoTareaRepository;

    @Autowired
    public EstadoTareaServiceImpl(EstadoTareaRepository estadoTareaRepository){
        this.estadoTareaRepository = estadoTareaRepository;
    }
    @Override
    public List<EstadoTarea> findAll() {
        return (List<EstadoTarea>) estadoTareaRepository.findAll();
    }
}
