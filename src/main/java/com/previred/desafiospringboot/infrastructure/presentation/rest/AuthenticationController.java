package com.previred.desafiospringboot.infrastructure.presentation.rest;

import com.previred.desafiospringboot.application.AuthenticationService;
import com.previred.desafiospringboot.infrastructure.presentation.rest.dto.AuthenticationRequest;
import com.previred.desafiospringboot.infrastructure.presentation.rest.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest request) throws AuthenticationException {
        String token = authenticationService.authenticate(request.getUsername(), request.getPassword());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}