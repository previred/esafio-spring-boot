package com.nuevospa.gestortareas.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
//import org.springdoc.core.SwaggerUiConfigParametersBuilder;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/auth/**") // Endpoints públicos
                .build();
    }
    
    @Bean
    public GroupedOpenApi secureApi() {
        return GroupedOpenApi.builder()
                .group("secure-api")
                .pathsToMatch("/api/**") // Endpoints seguros
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
                    return operation;
                })
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info().title("Gestor de Tareas API").version(appVersion));
    }
}
