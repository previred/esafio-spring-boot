package com.arturo.desafio_spring_boot.configs;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API Rest NUEVO SPA (Desafío previred)",
        version = "1.0.0",
        description = "API REST para administrar tareas con autenticación JWT"
    )
)
public class OpenApiConfig {
    
}
