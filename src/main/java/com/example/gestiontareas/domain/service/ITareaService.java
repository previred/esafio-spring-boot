package com.example.gestiontareas.domain.service;

import com.example.gestiontareas.domain.model.Tarea;

import java.util.List;

public interface ITareaService {

    public List<Tarea> findAll();
    public Tarea findById(Long id);
    public Tarea asignarTarea(Long tareaId, Long usuarioId);
    public Tarea create(Tarea tarea);
    public void deleteById(Long id);
    public List<Tarea> findByUserId(Long id);
    public Tarea finalizar(Long id);

}
