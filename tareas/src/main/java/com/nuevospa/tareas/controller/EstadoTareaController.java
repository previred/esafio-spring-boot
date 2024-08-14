package com.nuevospa.tareas.controller;

import com.nuevospa.tareas.entity.EstadoTareaEntity;
import com.nuevospa.tareas.model.EstadoTarea;
import com.nuevospa.tareas.service.EstadoTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados-tarea")
public class EstadoTareaController {

    @Autowired
    private EstadoTareaService estadoTareaService;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadoTareaEntity>> listarEstadosTarea() {
        List<EstadoTareaEntity> estadosTarea = estadoTareaService.listarEstadosTarea();
        return new ResponseEntity<>(estadosTarea, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<EstadoTarea> crearEstadoTarea(@RequestBody EstadoTarea estadoTarea) {
        EstadoTarea nuevoEstadoTarea = estadoTareaService.crearEstadoTarea(estadoTarea);
        return new ResponseEntity<>(nuevoEstadoTarea, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EstadoTareaEntity> actualizarEstadoTarea(@PathVariable Long id, @RequestBody EstadoTarea estadoTareaActualizado) {
        EstadoTareaEntity estadoTarea = estadoTareaService.actualizarEstadoTarea(id, estadoTareaActualizado);
        if(estadoTarea != null) {
            return new ResponseEntity<>(estadoTarea, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEstadoTarea(@PathVariable Long id) {
        boolean eliminado = estadoTareaService.eliminarEstadoTarea(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
