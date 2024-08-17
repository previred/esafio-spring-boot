package com.nuevo.spa.desafio_spring_boot.service;


import com.nuevo.spa.desafio_spring_boot.model.Tarea;
import com.nuevo.spa.desafio_spring_boot.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public Tarea crearTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public Tarea actualizarTarea(Long id, Tarea tareaActualizada) {
        return tareaRepository.findById(id)
                .map(tarea -> {
                    tarea.setTitulo(tareaActualizada.getTitulo());
                    tarea.setDescripcion(tareaActualizada.getDescripcion());
                    tarea.setFechaLimite(tareaActualizada.getFechaLimite());
                    tarea.setEstado(tareaActualizada.getEstado());
                    return tareaRepository.save(tarea);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con el id: " + id));
    }

    @Override
    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }
}
