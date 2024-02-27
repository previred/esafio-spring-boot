package com.example.demo.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        AuthResponse response = authService.login(request);
        // Verificar si el código de la respuesta es 200 (éxito)
        if (response.getCode() == 200) {
            return ResponseEntity.ok(response);
        } else {
            // Si el código de la respuesta no es 200, devolver un ResponseEntity con el código de estado apropiado
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
    	AuthResponse response = authService.register(request);
        // Verificar si el código de la respuesta es 200 (éxito)
        if (response.getCode() == 201) {
            return ResponseEntity.ok(response);
        } else {
            // Si el código de la respuesta no es 200, devolver un ResponseEntity con el código de estado apropiado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
