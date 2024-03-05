package com.example.desafio.service;

import com.example.desafio.model.entities.Tarea;
import com.example.desafio.model.response.ListadoTareasResponse;

import java.util.List;

public interface TareaService {
    List<ListadoTareasResponse> listar();
    void crearTarea(Tarea tarea);
    void actualizarTarea(Tarea tarea);
    void eliminarTarea(Integer idTarea);
}
