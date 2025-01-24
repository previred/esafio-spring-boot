package com.previred.desafioGestionTareas.controller;

import com.previred.desafioGestionTareas.dtos.ApiResponseDTO;
import com.previred.desafioGestionTareas.dtos.TareaDTO;
import com.previred.desafioGestionTareas.dtos.UsuarioDTO;
import com.previred.desafioGestionTareas.services.UsuarioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Operation(
            summary = "Registrar Usuario",
            description = "Registra un Usuario",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Registra un Usuario Nuevo",
                    required = true,
                    content = @Content(
                            mediaType = "application/json"

                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuario Registrado Correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TareaDTO.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuario no agregado")
            }
    )

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO> register(@RequestBody UsuarioDTO userDTO) {
        return usuarioServices.register(userDTO);
    }

    @Operation(
            summary = "Iniciar Sesion",
            description = "Inicio de Sesion con Usuario registrado",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Iniciar sesion con nombre de usuario y password registrada",
                    required = true,
                    content = @Content(
                            mediaType = "application/json"

                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Inicio de Sesion de Usuario",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TareaDTO.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Campo Usuario o contraseña incorrectos , favor reingresar")
            }
    )

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioServices.login(usuarioDTO);
    }

    //ONLY DEVELOPER METHOD FOR HELP DEBUGGIN
    @GetMapping("/onlytoken")
    public ResponseEntity<ApiResponseDTO> token() {
        return usuarioServices.onlytoken();
    }



}
