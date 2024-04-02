package com.previred.nspa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.previred.nspa.entity.Tareas;
import com.previred.nspa.exception.RecursoNoEncontradoException;
import com.previred.nspa.repository.TareasRepository;

@Service
public class TareasService {

    private final TareasRepository tareasRepository;

    public TareasService(TareasRepository tareasRepository) {
        this.tareasRepository = tareasRepository;
    }

    public List<Tareas> getAllTareas() {
        return Optional.ofNullable(tareasRepository.findAll())
                .orElseThrow(() -> new RecursoNoEncontradoException("Error al obtener todas las tareas"));
    }

    public Tareas getTareaById(Integer id) {
        return tareasRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Tarea no encontrada con id : " + id));
    }

    public Tareas saveTarea(Tareas tareas) {
        return Optional.ofNullable(tareasRepository.save(tareas))
                .orElseThrow(() -> new RecursoNoEncontradoException("Error al guardar la tarea"));
    }

    public void deleteTarea(Integer id) {
        Tareas tarea = getTareaById(id);
        Optional.ofNullable(tarea).ifPresent(t -> tareasRepository.delete(t));
    }
}