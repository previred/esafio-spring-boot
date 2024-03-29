package com.nuevospa.gestiontareas.auth;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentConfig {
    private final Environment environment;

    public EnvironmentConfig(Environment e) {
        this.environment = e;
    }

    public String getJwtSeed() {
        return environment.getProperty("JWT_SEED");
    }

    public String getJwtExpirationMilliseconds() {
        return environment.getProperty("JWT_EXPIRATION_MS");
    }
}
