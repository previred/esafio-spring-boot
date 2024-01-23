package com.previred.model.service;

import com.previred.model.entitys.Tarea;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TareaService {
    public ResponseEntity<?> agregarTarea(Tarea tarea);
    public ResponseEntity<?> agregarTareas(List<Tarea> tareas);
    public ResponseEntity<?> actualizarTarea(Tarea tarea);
    public ResponseEntity<?> eliminarTarea(Long id);
    public ResponseEntity<?> mostrarTarea(Long id);
}
