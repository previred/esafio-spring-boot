package com.arturo.desafio_spring_boot.helpers;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.arturo.desafio_spring_boot.entities.UsuarioEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {
    
    @Value("${security.jwt.secret-key}")
    private String jwtSecreString;

    public String createToken(UsuarioEntity usuarioEntity) {
        final var now = new Date();
        final var expirationDate = new Date(now.getTime() + (3600 * 1000));
        return Jwts
            .builder()
            .claim("userId", usuarioEntity.getId())
            .subject(usuarioEntity.getNombreUsuario())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .signWith(this.getSecretKey())
            .compact();
    }
    
    public Long getUsuarioIdFromToken(String token) {
        return Long.valueOf((int)this.signToken(token).get("userId"));
    }
    
    public boolean validateToken(String token) throws JwtException {
        final var expirationDate = this.getExpirationDateFromToken(token);
        return expirationDate.after(new Date());
    }

    public String getAccessTokenFromHeader(String headerAuthorization) {
        if (headerAuthorization.startsWith("Bearer ")) {
            return headerAuthorization.substring(7);
        }
        return "";
    }

    public String getUsernameFromToken(String token) throws JwtException {
        return this.getClaimsFromToken(token, Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token) throws JwtException {
        return this.getClaimsFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> resolver) throws JwtException {
        return resolver.apply(this.signToken(token));
    }

    private Claims signToken(String token) throws JwtException {
        return Jwts
            .parser()
            .verifyWith(this.getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(this.jwtSecreString.getBytes(StandardCharsets.UTF_8));
    }
}
