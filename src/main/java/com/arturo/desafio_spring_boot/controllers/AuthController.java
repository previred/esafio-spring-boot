package com.arturo.desafio_spring_boot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.arturo.desafio_spring_boot.dtos.TokenDto;
import com.arturo.desafio_spring_boot.dtos.UsuarioDto;
import com.arturo.desafio_spring_boot.helpers.JwtHelper;
import com.arturo.desafio_spring_boot.services.AuthService;
import io.jsonwebtoken.MalformedJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
@Tag(
    name = "Controlador de autenticación",
    description = "Controla el login de usuario generando un access token y valida el token"
)
public class AuthController {
    
    private final AuthService authService;
    private final JwtHelper jwtHelper;
    private static final String JWT_INVALIDO_MSG = "JWT inválido";

    @Operation(summary = "Generar un access token usando credenciales de usuario")
    @PostMapping(path = "login")
    public ResponseEntity<TokenDto> jwtCreate(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(this.authService.login(usuarioDto));
    }

    @Operation(summary = "Valida el access token del usuario")
    @PostMapping(path = "validate")
    public ResponseEntity<TokenDto> jwtValidate(@RequestHeader String authorization) {
        try {
            final var accessToken = this.jwtHelper.getAccessTokenFromHeader(authorization);
            if (accessToken.isBlank()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, JWT_INVALIDO_MSG);
            }
            return ResponseEntity.ok(
                this.authService.validateToken(
                    TokenDto
                        .builder()
                        .accessToken(accessToken)
                        .build()
                )
            );
        } catch (MalformedJwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, JWT_INVALIDO_MSG);
        }
    }

}
