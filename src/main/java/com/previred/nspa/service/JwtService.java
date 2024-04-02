package com.previred.nspa.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.previred.nspa.model.UsuariosDTO;

@Service
public class JwtService {

    private final String ISSUER = "tu_app";
    private final String SECRET_KEY = "secret"; // Deberías tener esto en una configuración segura

    public String generateToken(UsuariosDTO usuariosDTO) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 3600000); // 1 hora de validez
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuariosDTO.getNombreUsuario()) // Asume que UsuariosDTO tiene un campo nombreUsuario
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

}
