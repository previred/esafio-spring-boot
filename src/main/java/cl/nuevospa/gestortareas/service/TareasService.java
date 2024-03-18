package cl.nuevospa.gestortareas.service;

import cl.nuevospa.gestortareas.model.Tareas;

import java.util.List;
import java.util.Optional;

public interface TareasService {
    List<Tareas> findAllTareas();

    Optional<Tareas> findTareaById(Integer id);

    List<Tareas> findTareasByEstado(Integer idEstado);

    Void addTarea(Tareas tareas);

    Void updateTarea(Integer id, Tareas tareas);

    Void deleteTarea(Integer id);
}
