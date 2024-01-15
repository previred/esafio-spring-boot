package com.nuevoapp.prueba.domain.service;

import com.nuevoapp.prueba.domain.model.dto.login.LoginRequest;

public interface PasswordService {
    String getUserPassword(String email);
}
