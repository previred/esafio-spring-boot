package com.nuevospa.taskmanager.controller;

import io.swagger.annotations.ApiOperation;
import com.nuevospa.taskmanager.dto.*;
import com.nuevospa.taskmanager.service.LoginService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;




@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @ApiOperation("Login de usuario")
    public ResponseEntity<TokenResponse> login(@RequestBody Login login) {
        return ResponseEntity.ok(loginService.login(login));
    }

}
