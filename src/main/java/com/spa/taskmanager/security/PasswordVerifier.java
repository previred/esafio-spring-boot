package com.spa.taskmanager.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordVerifier {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String storedHash = "{bcrypt}$2a$10$DOWSDALsJx3JtC5OxApUjO";

        String rawPassword = "password";

        boolean matches = passwordEncoder.matches(rawPassword, storedHash);
        System.out.println("Password matches: " + matches);
    }
}
