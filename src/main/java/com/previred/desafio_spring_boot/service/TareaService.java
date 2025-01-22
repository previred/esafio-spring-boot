package com.previred.desafio_spring_boot.service;

import com.previred.desafio_spring_boot.Domain.Tarea;
import com.previred.desafio_spring_boot.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> getAllTasks() {
        return tareaRepository.findAll();
    }

    public Tarea getTaskById(Long id) {
        return tareaRepository.findById(id).orElse(null);
    }

    public Tarea createTask(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea updateTask(Long id, Tarea tarea) {
        Optional<Tarea> existingTask = tareaRepository.findById(id);
        if (existingTask.isPresent()) {
            Tarea updatedTask = existingTask.get();
            updatedTask.setNombre(tarea.getNombre());
            updatedTask.setDescripcion(tarea.getDescripcion());
            updatedTask.setEstado(tarea.getEstado());
            return tareaRepository.save(updatedTask);
        }
        return null;
    }

    public boolean deleteTask(Long id) {
        if (tareaRepository.existsById(id)) {
            tareaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

