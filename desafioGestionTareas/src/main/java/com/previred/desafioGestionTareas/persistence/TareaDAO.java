package com.previred.desafioGestionTareas.persistence;

import com.previred.desafioGestionTareas.entities.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaDAO {

    List<Tarea> findAll();

    Optional<Tarea> findById(Long id);

    void save(Tarea task);

    void deleteById(Long id);

}

