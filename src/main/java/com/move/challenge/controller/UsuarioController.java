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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.move.challenge.dto.ErrorResponseDto;
import com.move.challenge.dto.UsuarioDto;
import com.move.challenge.service.UsuarioService;

@RestController
@RequestMapping("/api/private")
public class UsuarioController {

   @Autowired
   private UsuarioService usuarioService;

   @GetMapping("/usuario/{id}")
   @ResponseBody
   @Operation(summary = "Obtener un usuario", description = "Obtener un usuario por id", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Get Data View", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<UsuarioDto> getUsuario(@PathVariable Long id) {
      return ResponseEntity.ok(usuarioService.getUsuarioById(id));
   }

   @GetMapping("/usuario")
   @ResponseBody
   @Operation(summary = "Todos los usuarios", description = "Obtener todos los usuarios", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Get Data View", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {
      return ResponseEntity.ok(usuarioService.getAllUsuarios());
   }

}
