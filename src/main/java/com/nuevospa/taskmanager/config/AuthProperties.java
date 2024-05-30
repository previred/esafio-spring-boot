package com.nuevospa.taskmanager.config;

import org.springframework.context.annotation.Configuration;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {

    private String secret;
    private Long expiration;
}
