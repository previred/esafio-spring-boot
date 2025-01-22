package com.previred.desafio_spring_boot.controller;

import com.previred.desafio_spring_boot.Domain.Tarea;
import com.previred.desafio_spring_boot.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ResponseEntity<List<Tarea>> getAllTasks() {
        return ResponseEntity.ok(tareaService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTaskById(@PathVariable Long id) {
        Tarea tarea = tareaService.getTaskById(id);
        return tarea != null ? ResponseEntity.ok(tarea) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Tarea> createTask(@RequestBody Tarea tarea) {
        Tarea createdTarea = tareaService.createTask(tarea);
        return ResponseEntity.status(201).body(createdTarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> updateTask(@PathVariable Long id, @RequestBody Tarea tarea) {
        Tarea updatedTarea = tareaService.updateTask(id, tarea);
        return updatedTarea != null ? ResponseEntity.ok(updatedTarea) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = tareaService.deleteTask(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
