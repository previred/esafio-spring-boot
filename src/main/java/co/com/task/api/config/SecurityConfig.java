package co.com.task.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityConfig {

    private String origin;
    private String secret;
    private Long expiration;
}
