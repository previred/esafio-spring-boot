package com.nuevospa.tareas.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ValidaPassword {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean verificarPassword(String passwordIngresada, String passwordAlmacenada) {
        return passwordEncoder.matches(passwordIngresada, passwordAlmacenada);
    }
}
