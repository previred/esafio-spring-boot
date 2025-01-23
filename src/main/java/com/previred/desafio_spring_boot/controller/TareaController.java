package com.previred.desafio_spring_boot.controller;

import com.previred.desafio_spring_boot.Domain.Tarea;
import com.previred.desafio_spring_boot.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ResponseEntity<List<Tarea>> getAllTasks() {
        List<Tarea> tareas = tareaService.getAllTasks()
                .stream()
                .filter(tarea -> tarea.getEstado() != null)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tareas);
    }

    @PostMapping("/create")
    public ResponseEntity<Tarea> createTask(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaService.createTask(tarea);
        return ResponseEntity.status(201).body(nuevaTarea);
    }

    @GetMapping("/gettask/{id}")
    public ResponseEntity<Tarea> getTaskById(@PathVariable Long id) {
        Tarea tarea = tareaService.getTaskById(id);
        if (tarea != null) {
            return ResponseEntity.ok(tarea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Tarea> updateTask(@PathVariable Long id, @RequestBody Tarea tarea) {
        Tarea tareaActualizada = tareaService.updateTask(id, tarea);
        if (tareaActualizada != null) {
            return ResponseEntity.ok(tareaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean eliminado = tareaService.deleteTask(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
