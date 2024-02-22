package com.previred.challenge.controllers;

import com.previred.challenge.dto.JwtResponseDTO;
import com.previred.challenge.dto.UserLoginRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {

    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Retrieve JWT when user exits in the system.")
    JwtResponseDTO authenticate(@Validated @RequestBody UserLoginRequestDTO userLoginRequestDTO);

}
