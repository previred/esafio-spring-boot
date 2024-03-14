package com.desafio.gestion.controller;

import com.desafio.gestion.config.auth.JwtUtil;
import com.desafio.gestion.dto.AuthRequest;
import com.desafio.gestion.service.JwtUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Auth", description = "Administración API Autenticación")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final JwtUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/gestion/authenticate")
    @Operation(
            summary = "Autenticar usuario",
            description = "Autenticar un usuario y generar un token JWT"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa", content = { @Content(schema = @Schema(implementation = Map.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas", content = { @Content(schema = @Schema(implementation = Map.class)) }),
            @ApiResponse(responseCode = "403", description = "Cuenta de usuario bloqueada", content = { @Content(schema = @Schema(implementation = Map.class)) })
    })
    public ResponseEntity<Map<String, String>> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);

            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Usuario o contraseña inválida");
        } catch (LockedException e) {
            throw new LockedException("Cuenta de usuario bloqueada");
        }
    }

}