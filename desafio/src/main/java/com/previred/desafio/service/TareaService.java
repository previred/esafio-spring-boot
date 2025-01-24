package com.previred.desafio.service;

import com.previred.desafio.entity.Tarea;
import com.previred.desafio.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea actualizar(Long id, Tarea nuevaTarea) {
        return tareaRepository.findById(id)
                .map(tarea -> {
                    tarea.setTitulo(nuevaTarea.getTitulo());
                    tarea.setDescripcion(nuevaTarea.getDescripcion());
                    tarea.setEstado(nuevaTarea.getEstado());
                    tarea.setUsuario(nuevaTarea.getUsuario());
                    return tareaRepository.save(tarea);
                }).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
    }

    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }
}
