package com.example.desafio_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.desafio_spring_boot.domain.auth.AuthRequest;
import com.example.desafio_spring_boot.domain.user.User;
import com.example.desafio_spring_boot.repository.UserRepository;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String getPass() {
        return passwordEncoder.encode("123");
    }

    public UserSecurityDetails authenticate(AuthRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()));

        User user = userRepository.findByUsername(input.getUsername())
                .orElseThrow();
        return new UserSecurityDetails(user);
    }
}
