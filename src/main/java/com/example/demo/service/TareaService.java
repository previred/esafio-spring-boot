package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tarea;
import com.example.demo.repository.TareaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> getAllTareas() {
        return tareaRepository.findAll();
    }

    public Optional<Tarea> getTareaById(Long id) {
        return tareaRepository.findById(id);
    }

    public Tarea createTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea updateTarea(Long id, Tarea tarea) {
        Optional<Tarea> existingTarea = tareaRepository.findById(id);
        if (existingTarea.isPresent()) {
            //tarea.setId(id);
            return tareaRepository.save(tarea);
        } else {
            throw new RuntimeException("Tarea not found");
        }
    }

    public boolean deleteTarea(Long id) {
        try {
        	tareaRepository.deleteById(id);
	        return true; // Se eliminó correctamente
	    } catch (Exception e) {
	        return false; // Hubo un error al eliminar
	    }
    }
}
