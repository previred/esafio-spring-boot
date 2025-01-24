package com.previred.desafioGestionTareas.persistence.Impl;

import com.previred.desafioGestionTareas.entities.Tarea;
import com.previred.desafioGestionTareas.persistence.TareaDAO;
import com.previred.desafioGestionTareas.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TareaDaoImpl implements TareaDAO {

    @Autowired
    private TareaRepository  tareaRepository;

    @Override
    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public void save(Tarea task) {
        tareaRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        tareaRepository.deleteById(id);
    }

}
