package com.previred.desafiobackend.presentation.controller.auth;

import com.previred.desafiobackend.domain.dto.auth.JwtResponse;
import com.previred.desafiobackend.domain.dto.auth.UserCredentials;
import com.previred.desafiobackend.domain.services.auth.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Allows a registered user to obtain a JWT Token.")
public class AuthenticationController {

    private JwtService jwtService;

    public AuthenticationController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserCredentials userCredentials) {
        return ResponseEntity.ok(jwtService.generateToken(userCredentials));
    }

}
