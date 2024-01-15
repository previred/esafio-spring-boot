package com.nuevoapp.prueba.domain.service;

import com.nuevoapp.prueba.domain.model.dto.UserDto;
import com.nuevoapp.prueba.domain.model.dto.login.LoginRequest;
import com.nuevoapp.prueba.domain.model.dto.login.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface UsersService {
    UserDto getUserByEmail(String email);

    ResponseEntity<LoginResponse> loginUser(LoginRequest request);
}
