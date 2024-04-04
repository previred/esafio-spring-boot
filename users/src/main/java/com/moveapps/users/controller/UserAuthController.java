package com.moveapps.users.controller;

import com.moveapps.users.dto.LoginResponse;
import com.moveapps.users.dto.LoginUserDto;
import com.moveapps.users.dto.RegisterUserDto;
import com.moveapps.users.model.User;
import com.moveapps.users.service.JwtService;
import com.moveapps.users.service.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private final UserAuthService userAuthService;
    private final JwtService jwtService;

    @Autowired
    public UserAuthController(JwtService jwtService, UserAuthService userAuthService) {
        this.jwtService = jwtService;
        this.userAuthService = userAuthService;
    }

    @Operation(summary = "Registra un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro Exitoso"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/signup")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = userAuthService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @Operation(summary = "Autentica un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación Exitosa"),
            @ApiResponse(responseCode = "401", description = "Credenciales Invalidas"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = userAuthService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        var loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
        return ResponseEntity.ok(loginResponse);
    }
}
