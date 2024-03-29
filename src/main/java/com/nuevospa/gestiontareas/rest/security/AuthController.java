package com.nuevospa.gestiontareas.rest.security;

import com.nuevospa.gestiontareas.auth.JwtTokenProvider;
import com.nuevospa.gestiontareas.auth.dto.JwtAuthenticationResponseDTO;
import com.nuevospa.gestiontareas.auth.dto.LoginRequestDTO;
import com.nuevospa.gestiontareas.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginRequestDTO sLogin =
                LoginRequestDTO.builder()
                        .username(SecurityUtils.sanitizeString(loginRequestDTO.getUsername()))
                        .password(SecurityUtils.sanitizeString(loginRequestDTO.getPassword()))
                        .build();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(sLogin.getUsername(), sLogin.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponseDTO(jwt));
    }
}
