package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tarea;
import com.example.demo.service.TareaService;

 
@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    // Obtener todas las tareas
    @GetMapping
    public ResponseEntity<List<Tarea>> getAllTareas() {
        List<Tarea> tareas = tareaService.getAllTareas();
        return ResponseEntity.ok(tareas);
    }

    // Obtener una tarea por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaService.getTareaById(id);
        return tarea.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva tarea
    @PostMapping
    public ResponseEntity<Tarea> createTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaService.createTarea(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
    }

    // Actualizar una tarea existente
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        Optional<Tarea> tareaActualizada = Optional.of(tareaService.updateTarea(id, tarea));
        return tareaActualizada.map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una tarea por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable Long id) {
        boolean eliminada = tareaService.deleteTarea(id);
        return eliminada ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

