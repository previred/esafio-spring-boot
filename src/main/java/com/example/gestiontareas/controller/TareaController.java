package com.example.gestiontareas.controller;

import com.example.gestiontareas.domain.model.Tarea;
import com.example.gestiontareas.domain.service.ITareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tarea")
@Tag(name = "Tareas")
public class TareaController {

    private final ITareaService tareaService;

    @Autowired
    public TareaController(ITareaService tareaService){
        this.tareaService = tareaService;
    }

    @Operation(
            summary = "Lista de todas las tareas"
    )
    @GetMapping("/tareas")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Tarea>> listar(){

        List<Tarea> tareasList = tareaService.findAll();
        if (ObjectUtils.isEmpty(tareasList)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tareasList, HttpStatus.OK);
    }

    @Operation(
            summary = "Detalle de una tarea en base a un ID"
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Tarea> detalle(@PathVariable Long id){

        Tarea tarea = tareaService.findById(id);
        if (ObjectUtils.isEmpty(tarea)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tarea, HttpStatus.OK);
    }

    @Operation(
            summary = "Asigna una tarea a un usuario, requiere ID del usuario y ID de la tarea"
    )
    @PutMapping("/asignar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Tarea> asignar(@RequestParam Long tareaId, @RequestParam Long usuarioId){

        Tarea tarea = tareaService.asignarTarea(tareaId, usuarioId);
        if (ObjectUtils.isEmpty(tarea)){
            return new ResponseEntity<>(tarea, HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(tarea, HttpStatus.ACCEPTED);
    }

    @Operation(
            summary = "Crea una tarea nueva"
    )
    @PostMapping("/tarea")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Tarea crear(@RequestBody Tarea tarea){

        tarea.setCreated(LocalDateTime.now());
        return tareaService.create(tarea);
    }

    @Operation(
            summary = "Elimina una tarea en base a un ID"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        if (ObjectUtils.isEmpty(tareaService.findById(id))){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        tareaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Lista de las tareas asociadas a un usuario, requiere el ID del usuario"
    )
    @GetMapping("/tareas/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Tarea>> listaByUsuario(@PathVariable Long id){

        List<Tarea> tareasList = tareaService.findByUserId(id);
        if (ObjectUtils.isEmpty(tareasList)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tareasList, HttpStatus.OK);
    }

    @Operation(
            summary = "El estado de la tarea pasa a estar finalizado"
    )
    @PutMapping("/finalizar/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Tarea> finalizarTarea(@PathVariable Long id){

        Tarea tarea = tareaService.finalizar(id);
        if (ObjectUtils.isEmpty(tarea)){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(tarea, HttpStatus.OK);
    }

}
