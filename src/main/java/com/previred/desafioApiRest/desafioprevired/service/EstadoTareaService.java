package com.previred.desafioApiRest.desafioprevired.service;

import com.previred.desafioApiRest.desafioprevired.repository.model.EstadoTarea;

import java.util.List;

public interface EstadoTareaService {
    EstadoTarea create(EstadoTarea estadoTarea);

    EstadoTarea findById(Long id);

    List<EstadoTarea> obtenerEstadoTare();
}
