package com.nuevo.spa.desafio_spring_boot.service;


import com.nuevo.spa.desafio_spring_boot.model.Tarea;
import java.util.List;
import java.util.Optional;

public interface TareaService {

    Tarea crearTarea(Tarea tarea);

    List<Tarea> obtenerTodasLasTareas();

    Optional<Tarea> obtenerTareaPorId(Long id);

    Tarea actualizarTarea(Long id, Tarea tarea);

    void eliminarTarea(Long id);
}
