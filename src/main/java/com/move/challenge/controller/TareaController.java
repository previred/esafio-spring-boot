package com.move.challenge.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.move.challenge.dto.ErrorResponseDto;
import com.move.challenge.dto.TareaCreateDto;
import com.move.challenge.dto.TareaDto;
import com.move.challenge.service.TareaService;

@RestController
@RequestMapping("/api/private")
public class TareaController {

   @Autowired
   private TareaService tareaService;

   @PostMapping("/tarea")
   @ResponseBody
   @Operation(summary = "Crear Tarea", description = "Crear una nueva tarea", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "201", description = "Json Tarea Dto", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<TareaDto> createTarea(@RequestBody TareaCreateDto tareaDto) {
      return new ResponseEntity<>(tareaService.createTarea(tareaDto), HttpStatus.CREATED);
   }

   @GetMapping("/tarea/{id}")
   @ResponseBody
   @Operation(summary = "Obtener un usuario", description = "Obtener un usuario por id", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Get Data View", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<TareaDto> getTarea(@PathVariable Long id) {
      return ResponseEntity.ok(tareaService.getTareaById(id));
   }

   @GetMapping("/tarea")
   @ResponseBody
   @Operation(summary = "Todas las tareas", description = "Obtener todas los tareas", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Get Data View", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<List<TareaDto>> getAllTareas() {
      return ResponseEntity.ok(tareaService.getAllTareas());
   }

   @GetMapping("/tarea/estado/{estadoId}")
   @ResponseBody
   @Operation(summary = "Todas las tareas por estado", description = "Obtener todas los tareas por estado especifco", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Get Data View", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<List<TareaDto>> getAllTareasByEstadi(@PathVariable Long estadoId) {
      return ResponseEntity.ok(tareaService.getAllTareasByEstado(estadoId));
   }

   @PutMapping("/tarea/{tareaId}/estado")
   @ResponseBody
   @Operation(summary = "Actualizar el estado de una tarea", description = "Actualizar el estado de una tarea", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Get Data View", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<TareaDto> updateTareaEstado(@PathVariable Long tareaId, @RequestParam Long estadoId) {
      return ResponseEntity.ok(tareaService.updateTareaEstado(tareaId, estadoId));
   }

   @PutMapping("/tarea/{tareaId}/usuario")
   @ResponseBody
   @Operation(summary = "Actualizar el usuario de una tarea", description = "Actualizar el usuario de una tarea", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Get Data View", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<TareaDto> updateTareaUsuario(@PathVariable Long tareaId, @RequestParam Long usuarioId) {
      return ResponseEntity.ok(tareaService.updateTareaUsuario(tareaId, usuarioId));
   }

   @PutMapping("/tarea/{tareaId}/finalizacion")
   @ResponseBody
   @Operation(summary = "Actualizar finalizacion", description = "Actualizar finalizacion de una tarea", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Get Data View", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<TareaDto> updateTareaUsuario(@PathVariable Long tareaId, @RequestParam String finalizacion) {
      return ResponseEntity.ok(tareaService.updateTareaFinalizacion(tareaId, finalizacion));
   }

   @DeleteMapping("/tarea/{id}")
   @ResponseBody
   @Operation(summary = "Eliminar una tarea", description = "Eliminar una tarea por su id", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Void", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<Void> deleteTarea(@PathVariable Long id) {
      return ResponseEntity.ok(tareaService.deleteTarea(id));
   }

}
