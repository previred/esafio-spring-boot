package com.nuevospa.gestortareas.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class SecretKeyProvider {

    private final SecretKey secretKey;

    public SecretKeyProvider() {
        this.secretKey = generateSecretKey();
    }

    private SecretKey generateSecretKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public SecretKey getSecretKey() {
        return this.secretKey;
    }
}
