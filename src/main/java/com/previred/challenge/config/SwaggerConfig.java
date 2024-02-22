package com.previred.challenge.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEMA = "bearerAuth";

    @Bean
    public OpenAPI bearerAuthDefinition() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEMA))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEMA, buildSecuritySchema())
                );
    }

    private SecurityScheme buildSecuritySchema() {
        return new SecurityScheme()
                .name(SECURITY_SCHEMA)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .description("Provide the JWT token.")
                .bearerFormat("JWT");
    }

}
