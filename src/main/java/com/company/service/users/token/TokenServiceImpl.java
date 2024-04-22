package com.company.service.users.token;

import com.company.configuration.security.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements  TokenService{

    private final TokenProperties tokenProperties;


    @Override
    public  String generateToken(String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("secret", UUID.randomUUID());
        Claims claims = Jwts.claims(payload).setSubject(email);
        var currentTime = Instant.now();
        Date issuedAt = Timestamp.from(currentTime);
        Instant expiredTime = currentTime.plusSeconds(tokenProperties.getExpiration());
        Date expiration = Timestamp.from(expiredTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(Keys.hmacShaKeyFor(tokenProperties.getSecret().getBytes()))
                .compact();
    }

}
