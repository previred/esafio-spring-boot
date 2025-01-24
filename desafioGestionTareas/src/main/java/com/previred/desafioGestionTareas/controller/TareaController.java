package com.previred.desafioGestionTareas.controller;

import com.previred.desafioGestionTareas.dtos.TareaDTO;
import com.previred.desafioGestionTareas.services.TareaServices;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tareas")
@Tag(name = "Tareas", description = "Controller para Tareas")
public class TareaController {

    @Autowired
    private TareaServices tareaServices;


    @Operation(
            summary = "Buscar tareas",
            description = "Retorna lista de tareas o vacio sino no existiera ninguna",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tareas asociadas",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TareaDTO.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = " no se encontraron Tareas asociadas")
            }
    )
    @GetMapping
    public ResponseEntity<List<TareaDTO>> findAll() {

        return new ResponseEntity<>(tareaServices.findAll(), HttpStatus.OK);
    }


    @Operation(
            summary = "Buscar tarea por id",
            description = "Retorna  tarea especifica por id",

            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tareas asociadas",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TareaDTO.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Tarea no asociadas")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        return new ResponseEntity<>(tareaServices.findById(id), HttpStatus.OK);
    }


    @Operation(
            summary = "Eliminar tarea por id",
            description = "Elimina tarea por id",

            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tarea eliminada correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TareaDTO.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Tarea no asociada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        tareaServices.deleteById(id);
        return new ResponseEntity<>("Eliminado exitosamente", HttpStatus.NO_CONTENT);
    }


    @Operation(
            summary = "Tarea Nueva",
            description = "Agrega una Nueva Tarea",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "se agrego una nueva tarea",
                    required = true,
                    content = @Content(
                            mediaType = "application/json"

                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tarea agregada correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TareaDTO.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Tarea no se pudo agregada")
            }
    )
    @PostMapping
    public ResponseEntity<?> save(@RequestBody TareaDTO taskDTO)  {

        tareaServices.save(taskDTO);
        return new ResponseEntity<>("Tarea Agregada con éxito", HttpStatus.CREATED);
    }


    @Operation(
            summary = "Actualiza tareas segun id",
            description = "Actualiza una tarea  segun id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "actualizo la tarea con el id correctamente",
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
                                    schema = @Schema(implementation = TareaDTO.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Tarea no asociada")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<?>update(@PathVariable Long id, @RequestBody TareaDTO tareaDTO) {

        //ALERT!! [Request method 'PUT' is not supported]
        return new ResponseEntity<>(tareaServices.update(id, tareaDTO), HttpStatus.OK);
    }


}
