package com.nuevospa.app.providers;

import com.nuevospa.app.configs.AppProperties;
import com.nuevospa.app.exceptions.AuthTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final AppProperties appProperties;

    @Autowired
    public JwtTokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getJwtExpirationMls() * 36L);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getJwtSecret())
                .compact();

    }

    public String getEmailFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(appProperties.getJwtSecret()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(appProperties.getJwtSecret()).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthTokenException(ex.getMessage());
        }
    }
}