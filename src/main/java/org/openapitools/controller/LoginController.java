package org.openapitools.controller;

import io.swagger.annotations.ApiOperation;
import org.openapitools.dto.Login;
import org.openapitools.dto.TokenResponse;
import org.openapitools.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @ApiOperation("Servicio para autenticar un usuario")
    public ResponseEntity<TokenResponse> login(@RequestBody Login login) {
        return ResponseEntity.ok(loginService.login(login));
    }
}
