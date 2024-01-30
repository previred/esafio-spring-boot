package cl.nuevo.spa.desafio.service;


import cl.nuevo.spa.desafio.model.EstadoTarea;

import java.util.List;
import java.util.Optional;

public interface EstadoTareaService {
    List<EstadoTarea> findAll();

    Optional<EstadoTarea> findById(Integer id);

    EstadoTarea save(EstadoTarea tarea);

    void delete(EstadoTarea tarea);
}
