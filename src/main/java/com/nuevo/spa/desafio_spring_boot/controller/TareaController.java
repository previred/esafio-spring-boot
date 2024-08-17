package com.nuevo.spa.desafio_spring_boot.controller;


import com.nuevo.spa.desafio_spring_boot.model.Tarea;
import com.nuevo.spa.desafio_spring_boot.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaService.crearTarea(tarea);
        //return ResponseEntity.ok(nuevaTarea);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }

        
    @GetMapping
    public ResponseEntity<List<Tarea>> listarTareas() {
        List<Tarea> tareas = tareaService.obtenerTodasLasTareas();
        return new ResponseEntity<>(tareas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTareaPorId(@PathVariable Long id) {
        return tareaService.obtenerTareaPorId(id)
                //.map(ResponseEntity::ok)
        		.map(tarea -> new ResponseEntity<>(tarea, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

        
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        try {
            Tarea tareaActualizada = tareaService.actualizarTarea(id, tarea);
            return new ResponseEntity<>(tareaActualizada, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

        
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        try {
            tareaService.eliminarTarea(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

