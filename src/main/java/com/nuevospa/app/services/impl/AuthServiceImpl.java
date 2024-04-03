package com.nuevospa.app.services.impl;

import com.nuevospa.app.dtos.request.SignInRequestDto;
import com.nuevospa.app.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Authentication authenticate(SignInRequestDto singInDTO) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(singInDTO.getEmail(), singInDTO.getPassword())
        );
    }
}