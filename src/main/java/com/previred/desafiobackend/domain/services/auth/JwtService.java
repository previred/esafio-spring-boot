package com.previred.desafiobackend.domain.services.auth;

import com.previred.desafiobackend.data.entities.User;
import com.previred.desafiobackend.domain.dto.auth.JwtResponse;
import com.previred.desafiobackend.domain.dto.auth.UserCredentials;
import com.previred.desafiobackend.domain.services.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@Service
@Log4j2
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    private UserService userService;

    public JwtService(UserService userService) {
        this.userService = userService;
    }

    public JwtResponse generateToken(UserCredentials credentials) {
        try {
            User loguedUser = userService.findByEmail(credentials.getUserEmail());
            String jwt = Jwts.builder()
                    .setSubject(loguedUser.getEmail())
                    .claim("role", loguedUser.getRole())
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plus(7, ChronoUnit.DAYS)))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
            return JwtResponse.builder().token(jwt).build();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String extractRoles(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role", String.class);
    }


}
