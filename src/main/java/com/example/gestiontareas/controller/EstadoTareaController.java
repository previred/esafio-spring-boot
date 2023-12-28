package com.example.gestiontareas.controller;

import com.example.gestiontareas.domain.model.EstadoTarea;
import com.example.gestiontareas.domain.service.IEstadoTareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/estado_tarea")
@Tag(name = "Estados de tarea")
public class EstadoTareaController {

    private final IEstadoTareaService estadoTareaService;

    @Autowired
    public EstadoTareaController(IEstadoTareaService estadoTareaService){
        this.estadoTareaService = estadoTareaService;
    }

    @Operation(
            summary = "Lista de los estados de una tarea"
    )
    @GetMapping("/estado_tareas")
    public ResponseEntity<List<EstadoTarea>> listar(){
        List<EstadoTarea> estadoTareasList = estadoTareaService.findAll();
        if (ObjectUtils.isEmpty(estadoTareasList)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(estadoTareasList, HttpStatus.OK);
    }
}
