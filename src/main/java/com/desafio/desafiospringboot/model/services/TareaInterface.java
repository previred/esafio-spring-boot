package com.desafio.desafiospringboot.model.services;

import com.desafio.desafiospringboot.model.dao.Tarea;

import java.util.*;

public interface TareaInterface {

    List<Tarea>  mostrarTodasLasTareas(Long id);
    Tarea crearTarea(Tarea tarea);
    Optional<Tarea> actualizarTarea(Long id, Tarea tarea);
    Optional<Tarea> borrarTarea(Long id);
}
