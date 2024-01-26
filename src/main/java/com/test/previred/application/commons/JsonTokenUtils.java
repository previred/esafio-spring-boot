package com.test.previred.application.commons;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JsonTokenUtils {

    private static final String SECRET = "my-32-character-ultra-secure-and-ultra-long-secret";
    private final static long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String CreateToken(String name, String email) {
        long expirationTime = EXPIRATION_TIME * 1_000;
        Date expiration = new Date(System.currentTimeMillis() + expirationTime);
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expiration)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();

    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

        } catch (JwtException e) {
            return null;
        }
    }
}
