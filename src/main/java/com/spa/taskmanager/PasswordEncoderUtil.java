package com.spa.taskmanager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password"); // Reemplaza "password" con tu contraseña
        System.out.println(hashedPassword);
    }
}
