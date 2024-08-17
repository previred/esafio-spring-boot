package com.nuevo.spa.desafio_spring_boot.controller;


import com.nuevo.spa.desafio_spring_boot.model.AuthRequestDto;
import com.nuevo.spa.desafio_spring_boot.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody AuthRequestDto authRequest) throws Exception {
        // Autenticar usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // Cargar detalles del usuario
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        // Generar token JWT
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }
}
