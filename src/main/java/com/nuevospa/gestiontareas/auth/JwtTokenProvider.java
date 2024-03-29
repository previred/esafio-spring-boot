package com.nuevospa.gestiontareas.auth;

import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Autowired
    private EnvironmentConfig vCfg;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = new UserPrincipal(authentication.getName());
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Long.parseLong(vCfg.getJwtExpirationMilliseconds()));

        return Jwts.builder()
                .setSubject(userPrincipal.getName())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, vCfg.getJwtSeed())
                .compact();
    }
}
