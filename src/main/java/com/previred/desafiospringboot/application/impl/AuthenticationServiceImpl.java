package com.previred.desafiospringboot.application.impl;

import com.previred.desafiospringboot.domain.model.User;
import com.previred.desafiospringboot.application.AuthenticationService;
import com.previred.desafiospringboot.infrastructure.persistance.UserRepository;
import com.previred.desafiospringboot.infrastructure.presentation.rest.exception.AuthenticationException;
import com.previred.desafiospringboot.infrastructure.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserRepository userRepository;


    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String authenticate(String username, String password) throws AuthenticationException {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        Optional<User> user = userRepository.findByUsername(username).stream().findFirst();
        if (user.isEmpty() || ! password.matches(user.get().getPassword())) {
            throw new AuthenticationException("Nombre de usuario o contraseña incorrectos");
        }
        String token = jwtTokenProvider.generateToken(user.get().getUsername());
        log.info("TOKEN GENERADO");
        return token;

    }
}