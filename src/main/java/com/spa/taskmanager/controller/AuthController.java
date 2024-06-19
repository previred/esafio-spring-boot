package com.spa.taskmanager.controller;



import com.spa.taskmanager.service.AuthService;
import org.openapitools.api.AuthApi;
import org.openapitools.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<String> authLoginPost(LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }
}


