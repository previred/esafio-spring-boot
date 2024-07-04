package com.example.demo.controller;


import com.example.demo.bussineslogic.service.TaskService;
import com.example.demo.configuration.response.GeneralResponse;
import com.example.demo.configuration.response.MessageResponse;
import com.example.demo.model.DTO.DtoTask;
import com.example.demo.model.DTO.DtoTaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/task")
public class TaskController {

    @Autowired
    private TaskService oTaskService;

    @Operation(summary = "Crea una nueva tarea con su estado correcpondiente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea creada corrrectamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Desautorizazdo.",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Prohibido.",
                    content = @Content)})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> createTask(@RequestBody DtoTask oDtoTask) {
        try {
            oTaskService.save(oDtoTask);
            return new ResponseEntity<>(new MessageResponse("Guardado correctamente."), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "Lista las todas las tareas que han sido creadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista lo que encontro",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DtoTaskResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Desautorizazdo.",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Prohibido.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Lista no encontrada",
                    content = @Content)})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DtoTaskResponse>> getAllTasks() {
        List<DtoTaskResponse> dtoTaskResponseList = oTaskService.findAll();
        return new ResponseEntity<>(dtoTaskResponseList, HttpStatus.OK);
    }

    @Operation(summary = "Obtener tarea por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DtoTaskResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Desautorizazdo.",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Prohibido.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada",
                    content = @Content)})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GeneralResponse> getTaskById(@PathVariable("id") Long id) {
        try {
            DtoTaskResponse taskResponse = oTaskService.getTaskById(id);
            return new ResponseEntity<>(taskResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse(e.getMessage()));
        }

    }

    @Operation(summary = "Actualiza la tarea.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizzada exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DtoTaskResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Desautorizazdo.",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Prohibido.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "EL registro a actualizazr no se encuentra.",
                    content = @Content)})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GeneralResponse> updateUser(@PathVariable("id") Long id, @RequestBody DtoTask oDtoTaskDetails) {
        try {
            oTaskService.update(id, oDtoTaskDetails);
            return new ResponseEntity<>(new MessageResponse("Actualizado correctamente."), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "Elimina la tarea.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea eliminada exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DtoTaskResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Desautorizazdo.",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Prohibido.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "EL registro a elimiminar no se encuentra.",
                    content = @Content)})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GeneralResponse> deleteTaskById(@PathVariable("id") Long id) {
        try {
            oTaskService.delete(id);
            return ResponseEntity.ok(new MessageResponse("Eliminado correctamente."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse(e.getMessage()));
        }
    }

}
