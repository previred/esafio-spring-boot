package com.arturo.desafio_spring_boot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.arturo.desafio_spring_boot.dtos.TareaDto;
import com.arturo.desafio_spring_boot.entities.TareaEntity;
import com.arturo.desafio_spring_boot.exception.DataNotFoundException;
import com.arturo.desafio_spring_boot.exception.DataNotProvidedException;
import com.arturo.desafio_spring_boot.services.TareaService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "tarea")
@AllArgsConstructor
public class TareaController {

    private final TareaService tareaService;

    @Operation(summary = "Obtiene una lista de todas las tareas")
    @GetMapping()
    public ResponseEntity<List<TareaEntity>> getTareas() {
        return ResponseEntity.ok(this.tareaService.getAll());
    }

    @Operation(summary = "Obtiene una lista de tareas filtrando por el id de estado")
    @GetMapping("{estadoId}")
    public ResponseEntity<List<TareaEntity>> getTareas(@PathVariable Long estadoId) {
        return ResponseEntity.ok(this.tareaService.getByEstado(estadoId));
    }

    @Operation(summary = "Permite crear una tarea")
    @PostMapping()
    public ResponseEntity<TareaEntity> create(@RequestBody TareaDto tarea) {
        try {
            return ResponseEntity.ok(this.tareaService.create(tarea));
        } catch (DataNotProvidedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Operation(summary = "Permite actualizar totalmente una tarea existente (entregando todos los datos)")
    @PutMapping()
    public ResponseEntity<TareaEntity> update(@RequestBody TareaDto tarea) {
        try {
            return ResponseEntity.ok(this.tareaService.update(tarea));
        } catch (DataNotProvidedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (DataNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Permite actualizar parcialmente una tarea existente")
    @PatchMapping()
    public ResponseEntity<TareaEntity> patch(@RequestBody TareaDto tarea) {
        try {
            return ResponseEntity.ok(this.tareaService.patch(tarea));
        } catch (DataNotProvidedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (DataNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Permite eliminar una tarea existente")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<TareaEntity> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.tareaService.delete(id));
        } catch (DataNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    

}
