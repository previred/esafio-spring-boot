package com.example.gestiontareas.domain.service;

import com.example.gestiontareas.domain.model.EstadoTarea;

import java.util.List;

public interface IEstadoTareaService {

    public List<EstadoTarea> findAll();
}
