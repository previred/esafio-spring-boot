package com.nuevo.spa.desafio_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nuevo.spa.desafio_spring_boot.model.EstadoTarea;
import com.nuevo.spa.desafio_spring_boot.service.EstadoTareaService;

import java.util.List;

@RestController
@RequestMapping("/api/estados-tarea")
public class EstadoTareaController {
    @Autowired
    private EstadoTareaService estadoTareaService;

    @PostMapping
    public ResponseEntity<EstadoTarea> crearEstadoTarea(@RequestBody EstadoTarea estadoTarea) {
        EstadoTarea nuevoEstadoTarea = estadoTareaService.crearEstadoTarea(estadoTarea);
        return new ResponseEntity<>(nuevoEstadoTarea, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoTarea> obtenerEstadoTarea(@PathVariable Long id) {
        return estadoTareaService.encontrarEstadoTareaPorId(id)
            .map(estadoTarea -> new ResponseEntity<>(estadoTarea, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<EstadoTarea>> listarEstadosTarea() {
        List<EstadoTarea> estadosTarea = estadoTareaService.listarEstadosTarea();
        return new ResponseEntity<>(estadosTarea, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoTarea> actualizarEstadoTarea(@PathVariable Long id, @RequestBody EstadoTarea estadoTarea) {
        try {
            EstadoTarea estadoTareaActualizado = estadoTareaService.actualizarEstadoTarea(id, estadoTarea);
            return new ResponseEntity<>(estadoTareaActualizado, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstadoTarea(@PathVariable Long id) {
        try {
            estadoTareaService.eliminarEstadoTarea(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
