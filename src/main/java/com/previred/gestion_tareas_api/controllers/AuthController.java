package com.previred.gestion_tareas_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.gestion_tareas_api.dtos.ApiResponseDTO;
import com.previred.gestion_tareas_api.dtos.TaskDTO;
import com.previred.gestion_tareas_api.dtos.UserDTO;
import com.previred.gestion_tareas_api.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {

   @Autowired
   private UserService userService;

   @Operation(
            summary = "Registrar usuario",
            description = "Registra un usuario",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "registra un usuario",
                    required = true,
                    content = @Content(
                            mediaType = "application/json"
                            
                    )
            ),
            responses = {
                @ApiResponse(
                    responseCode = "200", 
                    description = "Usuario agregado",
                    content = @Content(
                        mediaType = "application/json", 
                        schema = @Schema(implementation = TaskDTO.class))),
                @ApiResponse(
                    responseCode = "404", 
                    description = "Usuario no agregado")
            }
    )
    
   @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @Operation(
            summary = "Iniciar Sesion",
            description = "Iniciar sesion con usuario registrado",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Iniciar sesion con username y password",
                    required = true,
                    content = @Content(
                            mediaType = "application/json"
                            
                    )
            ),
            responses = {
                @ApiResponse(
                    responseCode = "200", 
                    description = "Usuario inisio sesion",
                    content = @Content(
                        mediaType = "application/json", 
                        schema = @Schema(implementation = TaskDTO.class))),
                @ApiResponse(
                    responseCode = "404", 
                    description = "Usuario o contraseña incorrectos")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }
    


}
