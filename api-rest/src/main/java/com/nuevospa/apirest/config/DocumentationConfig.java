package com.nuevospa.apirest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class DocumentationConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Desafío Técnico: Gestión de Tareas con Spring Boot y Java")
                        .version("1.0")
                        .description("La empresa NUEVO SPA desea desarrollar una plataforma de gestión de tareas para mejorar la productividad de sus equipos. El sistema debe permitir a los usuarios crear, actualizar, eliminar y listar tareas. Además, se requiere autenticación mediante JWT y documentación de la API utilizando OpenAPI y Swagger.")
                        .contact(new Contact()
                                .name("pablo.hirsh")
                                .email("pablo.hirsh")
                                .url("https://www.linkedin.com/in/pablohirsh/"))
                        .termsOfService("teminos del servicio")
                )
                .servers(List.of(new Server()
                                        .description("servidor desarrollo local")
                                        .url("http://localhost:8080/")
                                )
                );
    }
}
