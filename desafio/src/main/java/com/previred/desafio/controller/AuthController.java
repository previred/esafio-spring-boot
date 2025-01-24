package com.previred.desafio.controller;

import com.previred.desafio.entity.Usuario;
import com.previred.desafio.model.AuthResponse;
import com.previred.desafio.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Api(value = "AuthController", tags = "Autenticación", description = "Operaciones relacionadas con la autenticación")
public class AuthController {

    @Autowired
    private AuthService jwtAuthService;

    @Operation(
            summary = "Autenticación de usuarios",
            description = "Genera un token JWT válido para un usuario autenticado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa, devuelve el token"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody Usuario usuario) {
        String token = jwtAuthService.generateToken(usuario);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
