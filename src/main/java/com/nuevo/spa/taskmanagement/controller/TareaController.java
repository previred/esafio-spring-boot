package com.nuevo.spa.taskmanagement.controller;

import com.nuevo.spa.taskmanagement.constants.Messages;
import com.nuevo.spa.taskmanagement.dto.TareaDTO;
import com.nuevo.spa.taskmanagement.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Messages.BASE_PATH_TAREAS)
@Tag(name = Messages.TAG_TAREAS_NAME, description = Messages.TAG_TAREAS_DESCRIPTION)
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    @Operation(summary = Messages.GET_ALL_TAREAS_SUMMARY)
    public List<TareaDTO> getAllTareas() {
        return tareaService.getAllTareas();
    }

    @GetMapping("/{id}")
    @Operation(summary = Messages.GET_TAREA_BY_ID_SUMMARY)
    public ResponseEntity<?> getTareaById(@PathVariable Long id) {
        TareaDTO tareaDTO = tareaService.getTareaById(id);
        if (tareaDTO != null) {
            return ResponseEntity.ok(tareaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(String.format(Messages.TAREA_NOT_FOUND, id));
        }
    }

    @PostMapping
    @Operation(summary = Messages.CREATE_TAREA_SUMMARY)
    public ResponseEntity<?> createTarea(@RequestBody TareaDTO tareaDTO) {
        TareaDTO createdTarea = tareaService.createTarea(tareaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdTarea);
    }

    @PutMapping("/{id}")
    @Operation(summary = Messages.UPDATE_TAREA_SUMMARY)
    public ResponseEntity<?> updateTarea(@PathVariable Long id, @RequestBody TareaDTO tareaDTO) {
        TareaDTO updatedTareaDTO = tareaService.updateTarea(id, tareaDTO);
        if (updatedTareaDTO != null) {
            return ResponseEntity.ok(updatedTareaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(String.format(Messages.TAREA_NOT_FOUND, id));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = Messages.DELETE_TAREA_SUMMARY)
    public ResponseEntity<?> deleteTarea(@PathVariable Long id) {
        if (tareaService.deleteTarea(id)) {
            return ResponseEntity.ok(Messages.TAREA_ELIMINADA);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(String.format(Messages.TAREA_NOT_FOUND, id));
        }
    }
}
