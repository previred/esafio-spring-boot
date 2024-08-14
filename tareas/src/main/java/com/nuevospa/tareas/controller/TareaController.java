package com.nuevospa.tareas.controller;

import com.nuevospa.tareas.entity.TareaEntity;
import com.nuevospa.tareas.model.Tarea;
import com.nuevospa.tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/listar")
    public ResponseEntity<List<TareaEntity>> listarTareas() {
        List<TareaEntity> tareas = tareaService.listarTareas();
        return new ResponseEntity<>(tareas, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaService.crearTarea(tarea);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TareaEntity> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaActualizada) {
        TareaEntity tarea = tareaService.actualizarTarea(id, tareaActualizada);
        if(tarea != null) {
            return new ResponseEntity<>(tarea, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        boolean eliminado = tareaService.eliminarTarea(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
