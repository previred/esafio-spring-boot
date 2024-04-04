package com.moveapps.pe.service;

import com.moveapps.pe.dto.request.SignUpRequest;
import com.moveapps.pe.dto.request.SigninRequest;
import com.moveapps.pe.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
