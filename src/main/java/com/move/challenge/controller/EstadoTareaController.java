package com.move.challenge.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.move.challenge.dto.ErrorResponseDto;
import com.move.challenge.dto.EstadoTareaDto;
import com.move.challenge.service.EstadoTareaService;

@RestController
@RequestMapping("/api/public")
public class EstadoTareaController {

   @Autowired
   public EstadoTareaService estadoTareaService;

   @GetMapping("/estado-tarea")
   @ResponseBody
   @Operation(summary = "Get Estados tarea", description = "Obtener todos los estados en que puede estar una tarea.", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Get Data View", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<List<EstadoTareaDto>> getEstadosTarea() {
      return ResponseEntity.ok(estadoTareaService.getAllEstadosTarea());
   }

}
