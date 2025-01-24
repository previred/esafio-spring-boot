package com.previred.desafio.controller;

import com.previred.desafio.dto.TareaDto;
import com.previred.desafio.enums.ECatalogo;
import com.previred.desafio.exceptions.CustomException;
import com.previred.desafio.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TareaController.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

@RestController
@RequestMapping("/api/tareas")
@Tag(name = "Gestión de Tareas", description = "API para la gestión de tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    @Operation(summary = "Obtener todas las tareas")
    public List<TareaDto> getAllTareas() {
        return tareaService.getAllTareas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una tarea por ID")
    public ResponseEntity<TareaDto> getTareaById(@PathVariable Long id) {
        try {
            TareaDto tareaDTO = tareaService.getTareaById(id);
            return ResponseEntity.ok(tareaDTO);
        } catch (Exception e) {
            throw new CustomException(
                ECatalogo.ERROR_ID_CLIENTE.getCode(),
                String.format("La tarea con ID %d no fue encontrada.", id)
            );
        }
    }

    @PostMapping
    @Operation(summary = "Crear una nueva tarea")
    public ResponseEntity<TareaDto> createTarea(@RequestBody TareaDto tareaDTO) {
        try {
            TareaDto createdTarea = tareaService.createTarea(tareaDTO);
            return ResponseEntity.status(201).body(createdTarea);
        } catch (Exception e) {
            throw new CustomException(
                ECatalogo.ERROR_DESCONOCIDO.getCode(),
                ECatalogo.ERROR_DESCONOCIDO.getMessage()
            );
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una tarea existente")
    public ResponseEntity<TareaDto> updateTarea(@PathVariable Long id, @RequestBody TareaDto tareaDTO) {
        try {
            TareaDto updatedTarea = tareaService.updateTarea(id, tareaDTO);
            return ResponseEntity.ok(updatedTarea);
        } catch (Exception e) {
            throw new CustomException(
                ECatalogo.ERROR_ID_CLIENTE.getCode(),
                String.format("La tarea con ID %d no fue encontrada.", id)
            );
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una tarea existente")
    public ResponseEntity<String> deleteTarea(@PathVariable Long id) {
        try {
            if (tareaService.deleteTarea(id)) {
                return ResponseEntity.ok("Tarea eliminada exitosamente.");
            } else {
                throw new CustomException(
                    ECatalogo.ERROR_ID_CLIENTE.getCode(),
                    String.format("La tarea con ID %d no fue encontrada.", id)
                );
            }
        } catch (Exception e) {
            throw new CustomException(
                ECatalogo.ERROR_DESCONOCIDO.getCode(),
                ECatalogo.ERROR_DESCONOCIDO.getMessage()
            );
        }
    }
}
