package com.example.desafio_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio_spring_boot.domain.auth.AuthRequest;
import com.example.desafio_spring_boot.domain.auth.LoginResponse;
import com.example.desafio_spring_boot.service.AuthenticationService;
import com.example.desafio_spring_boot.service.JwtService;
import com.example.desafio_spring_boot.service.UserSecurityDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Gestiona la autenticación de los usuarios")
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Autenticación de usuarios y obtención de un JWT. Los usuarios de prueba son: user1, user2, user3. La contraseña es para los tres es: 123")
    @PostMapping
    public ResponseEntity<LoginResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        UserSecurityDetails authenticatedUser = authenticationService.authenticate(authRequest);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
