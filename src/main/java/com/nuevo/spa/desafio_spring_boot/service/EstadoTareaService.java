package com.nuevo.spa.desafio_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.nuevo.spa.desafio_spring_boot.model.EstadoTarea;
import com.nuevo.spa.desafio_spring_boot.repository.EstadoTareaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoTareaService {
    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    public EstadoTarea crearEstadoTarea(EstadoTarea estadoTarea) {
        return estadoTareaRepository.save(estadoTarea);
    }

    public Optional<EstadoTarea> encontrarEstadoTareaPorId(Long id) {
        return estadoTareaRepository.findById(id);
    }

    public List<EstadoTarea> listarEstadosTarea() {
        return estadoTareaRepository.findAll();
    }

    public EstadoTarea actualizarEstadoTarea(Long id, EstadoTarea estadoTareaActualizado) {
        if (estadoTareaRepository.existsById(id)) {
            estadoTareaActualizado.setId(id);
            return estadoTareaRepository.save(estadoTareaActualizado);
        } else {
            throw new ResourceNotFoundException("EstadoTarea con ID " + id + " no encontrado");
        }
    }

    public void eliminarEstadoTarea(Long id) {
        if (estadoTareaRepository.existsById(id)) {
            estadoTareaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("EstadoTarea con ID " + id + " no encontrado");
        }
    }
}

