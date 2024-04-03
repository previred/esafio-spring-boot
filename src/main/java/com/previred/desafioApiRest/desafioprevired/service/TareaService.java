package com.previred.desafioApiRest.desafioprevired.service;

import com.previred.desafioApiRest.desafioprevired.repository.model.Tarea;

import java.util.List;

public interface TareaService {


    Tarea save(Tarea tarea);

    Tarea update(Tarea tarea);

    List<Tarea> listTareas();

    List<Tarea> listTareasByUuarioId(Long idUsuario);

    Tarea findById(Long id);

    void delete(Long id);
}
