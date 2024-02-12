package com.move.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.move.challenge.dto.ErrorResponseDto;
import com.move.challenge.dto.JwtTokenDto;
import com.move.challenge.dto.UsuarioLoginDto;
import com.move.challenge.entity.UsuarioEntity;
import com.move.challenge.service.UsuarioService;
import com.move.challenge.utils.JwtUtils;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

   @Autowired
   private UsuarioService usuarioService;

   @Autowired
   private JwtUtils jwtUtils;

   @PostMapping("/login")
   @ResponseBody
   @Operation(summary = "Ontener token - Login", description = "Obtener un token de acceso")
   @ApiResponses(value = { //
         @ApiResponse(responseCode = "200", description = "Get a token access", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema
               = @Schema(implementation = JwtTokenDto.class))),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(implementation = ErrorResponseDto.class))) })
   public ResponseEntity<JwtTokenDto> generateToken(@RequestBody UsuarioLoginDto usuarioLoginDto) {

      UsuarioEntity usuarioEntity = usuarioService.getByEmailAndClave(usuarioLoginDto.getEmail(), usuarioLoginDto.getClave());

      return ResponseEntity.ok(jwtUtils.generateJwtToken(usuarioEntity));
   }


}