package com.nuevospa.desafiospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nuevospa.desafiospringboot.security.JwtRequest;
import com.nuevospa.desafiospringboot.security.JwtResponse;
import com.nuevospa.desafiospringboot.security.JwtTokenUtil;
import com.nuevospa.desafiospringboot.service.impl.JwtUserDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	@Operation(summary = "Crea un token de autenticación", description = "Autentica a un usuario y devuelve un token JWT", responses = {
			@ApiResponse(description = "Token JWT generado con éxito", responseCode = "200", content = @Content(schema = @Schema(implementation = JwtResponse.class))),
			@ApiResponse(description = "Credenciales inválidas", responseCode = "401") }, security = @SecurityRequirement(name = "bearerAuth"), requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Solicitud de autenticación", required = true, content = @Content(schema = @Schema(implementation = JwtRequest.class), examples = @ExampleObject(name = "EjemploSolicitudAutenticacion", summary = "Ejemplo de solicitud de autenticación", description = "Ejemplo completo de cómo autenticar un usuario proporcionando su nombre de usuario y contraseña.", value = """
					{
					"username": "felipeA",
					"password": "12345678A"
					}
					"""))))
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
