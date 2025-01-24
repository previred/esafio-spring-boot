package com.previred.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.api.config.JwtUtil;
import com.previred.api.dtos.AuthRequestDTO;
import com.previred.api.dtos.UsuarioDTO;
import com.previred.api.models.Usuarios;
import com.previred.api.services.AuthApi;
import com.previred.api.services.UsuariosService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsuariosController implements AuthApi {

    private final UsuariosService usuariosService;
    private final JwtUtil jwtService;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseEntity<String> registrarse(@RequestBody UsuarioDTO usuario) {
        String response = usuariosService.agregarUsuario(objectMapper.convertValue(usuario, Usuarios.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<String> generarToken(@RequestBody AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new UsernameNotFoundException("Usuario invalido!");
        }
    }

}
