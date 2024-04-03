package com.nuevospa.app.services;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {
    String generateToken(Authentication authentication);
}