package com.nuevoapp.prueba.controller;

import com.nuevoapp.prueba.config.error.dto.CustomError;
import com.nuevoapp.prueba.domain.model.dto.login.LoginRequest;
import com.nuevoapp.prueba.domain.model.dto.login.LoginResponse;
import com.nuevoapp.prueba.domain.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(("/auth"))
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;

    @Operation(
            summary = "Validates credentials and returns Auth Token",
            description = "Validates credentials and returns Auth Token",
            tags = {"Login"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful Login",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponse.class)
                            )),
                    @ApiResponse(
                            responseCode = "204",
                            description = "User/password not found"
                            ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Invalid credentials",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomError.class)
                            ))
            })

    @PostMapping()
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("call to loginUser: {}", loginRequest.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()));

        return usersService.loginUser(loginRequest);
    }
}
