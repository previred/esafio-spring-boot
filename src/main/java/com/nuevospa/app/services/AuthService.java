package com.nuevospa.app.services;

import com.nuevospa.app.dtos.request.SignInRequestDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication authenticate(SignInRequestDto singInDTO);
}