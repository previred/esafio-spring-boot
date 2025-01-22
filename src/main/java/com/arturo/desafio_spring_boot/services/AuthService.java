package com.arturo.desafio_spring_boot.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.arturo.desafio_spring_boot.dtos.TokenDto;
import com.arturo.desafio_spring_boot.dtos.UsuarioDto;
import com.arturo.desafio_spring_boot.entities.UsuarioEntity;
import com.arturo.desafio_spring_boot.helpers.JwtHelper;
import com.arturo.desafio_spring_boot.interfaces.AuthServiceInterface;
import com.arturo.desafio_spring_boot.repositories.UsuarioRepository;

import io.jsonwebtoken.JwtException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@AllArgsConstructor
@Slf4j
public class AuthService implements AuthServiceInterface {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    private static final String UNAUTHORIZED_USER_MSG = "Unauthorized user";

    @Override
    public TokenDto login(UsuarioDto user) {
        final var usuarioDB = this.usuarioRepository.findByNombreUsuario(user.getNombreUsuario())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "WFT"));

        this.validPassword(user, usuarioDB);
        return TokenDto
            .builder()
            .accessToken(this.jwtHelper.createToken(usuarioDB))
            .build();
    }

    @Override
    public TokenDto validateToken(TokenDto token) {
        try {
            final var accessToken = token.getAccessToken();
            if (this.jwtHelper.validateToken(accessToken)) {
                return TokenDto.builder().accessToken(accessToken).build();
            }
        } catch (JwtException e) {
            log.info("[validateToken] jwtException: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_MSG);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_MSG);
    }

    private void validPassword(UsuarioDto usuario, UsuarioEntity usuarioEntity){
        if (!this.passwordEncoder.matches(usuario.getPassword(), usuarioEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_MSG);
        }
    }
    
}
