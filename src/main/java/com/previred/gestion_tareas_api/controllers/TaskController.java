package com.previred.gestion_tareas_api.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.previred.gestion_tareas_api.dtos.TaskDTO;
import com.previred.gestion_tareas_api.services.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(path = "api/v1/tasks")
@Tag(name = "Tasks", description = "Controller for Tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


     @Operation(
            summary = "Buscar todas las tareas",
            description = "Devuelve todas las tareas o un arreglo vacio en caso de no existir ninguna",
            responses = {
                @ApiResponse(
                    responseCode = "200", 
                    description = "Tareas encontradas",
                    content = @Content(
                        mediaType = "application/json", 
                        schema = @Schema(implementation = TaskDTO.class))),
                @ApiResponse(
                    responseCode = "404", 
                    description = "Tareas no encontradas")
            }
    )
    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll() {
        
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @Operation(
            summary = "Buscar tareas por id",
            description = "Devuelve una tarea especifica segun id",

            responses = {
                @ApiResponse(
                    responseCode = "200", 
                    description = "Tarea encontrada",
                    content = @Content(
                        mediaType = "application/json", 
                        schema = @Schema(implementation = TaskDTO.class))),
                @ApiResponse(
                    responseCode = "404", 
                    description = "Tarea no encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        
        return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Eliminar tareas por id",
            description = "Elimina una tarea especifica segun id",
            
            responses = {
                @ApiResponse(
                    responseCode = "200", 
                    description = "Tarea eliminada",
                    content = @Content(
                        mediaType = "application/json", 
                        schema = @Schema(implementation = TaskDTO.class))),
                @ApiResponse(
                    responseCode = "404", 
                    description = "Tarea no encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) { //metodo pasarlo a response entity
            taskService.deleteById(id);
        return new ResponseEntity<>("Eliminado con éxito", HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Agregar tarea",
            description = "Agrega una tarea",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "agrego una tarea",
                    required = true,
                    content = @Content(
                            mediaType = "application/json"
                            
                    )
            ),
            responses = {
                @ApiResponse(
                    responseCode = "200", 
                    description = "Tarea agregada",
                    content = @Content(
                        mediaType = "application/json", 
                        schema = @Schema(implementation = TaskDTO.class))),
                @ApiResponse(
                    responseCode = "404", 
                    description = "Tarea no agregada")
            }
    )
    @PostMapping
    public ResponseEntity<?> save(@RequestBody TaskDTO taskDTO)  { 

        taskService.save(taskDTO);
        return new ResponseEntity<>("Agregado con éxito", HttpStatus.CREATED);
    }
    
    @Operation(
            summary = "Actualizar tareas por id",
            description = "Actualiza una tarea especifica segun id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "actualizo la tarea con el id",
                    required = true,
                    content = @Content(
                            mediaType = "application/json"
                            
                    )
            ),
            responses = {
                @ApiResponse(
                    responseCode = "200", 
                    description = "Tarea actualizada",
                    content = @Content(
                        mediaType = "application/json", 
                        schema = @Schema(implementation = TaskDTO.class))),
                @ApiResponse(
                    responseCode = "404", 
                    description = "Tarea no encontrada")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TaskDTO taskDTO) { 
        
        return new ResponseEntity<>(taskService.update(id, taskDTO), HttpStatus.OK);
    }
    

}
