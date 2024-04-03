package com.nuevospa.app.services.impl;


import com.nuevospa.app.providers.JwtTokenProvider;
import com.nuevospa.app.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtTokenServiceImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String generateToken(Authentication authentication) {
        return jwtTokenProvider.generateToken(authentication);
    }
}