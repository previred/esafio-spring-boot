package com.previred.desafio.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

/**
 * OpenApiConfig.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

@SecurityScheme(
    name = "Authorization",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(createApiInfo());
    }

    private Info createApiInfo() {
        return new Info()
            .title("Gestión de Tareas API")
            .description("La empresa NUEVO SPA desea desarrollar una plataforma de gestión de tareas para mejorar la productividad de sus equipos. El sistema debe permitir a los usuarios crear, actualizar, eliminar y listar tareas")
            .version("v1")
            .license(new License().name("Unlicense").url("https://unlicense.org"))
            .contact(createContact());
    }

    private Contact createContact() {
        return new Contact()
            .name("Jimmy Villa")
            .email("jimmy.villa7x@gmail.com");
    }
}