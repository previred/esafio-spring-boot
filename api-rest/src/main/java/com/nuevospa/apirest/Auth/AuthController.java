package com.nuevospa.apirest.Auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autentificación JWT", description = "Acciones de registro de usuario e identificación, genera token")
public class AuthController {

	private final AuthService authService;

	@Operation(
		summary = "Autentificación del usuario", description = "Autentificación de las credenciales y crea el token de validación")
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class)) }),
			@ApiResponse(responseCode = "403", description = "Usuario/password no valido" ,content = { @Content(mediaType = "none", schema = @Schema(implementation = HashMap.class))  } )
		})
	@PostMapping(value = "login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
	{
		return ResponseEntity.ok(authService.login(request));
	}

	@Operation(
			summary = "Registro del usuario", description = "Permite la creación del usuario y crea el token de validación")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class)) }),
					@ApiResponse(responseCode = "403", description = "Error al registrar el usuario",content = { @Content(mediaType = "none", schema = @Schema(implementation = HashMap.class)) } )
			})
	@PostMapping(value = "register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
	{
		return ResponseEntity.ok(authService.register(request));
	}
}