package com.previred.desafioGestionTareas.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "DESAFIO GESTIÓN TAREAS PREVIRED",
                description = "CRUD api para gestor de tareas para cliente NUEVO SPA con JWT y OPENAPI ",
                termsOfService = "https://www.NUEVO_SPA.com/terms-of-service/",
                version = "0.0.1",
                contact = @Contact(
                        name = "Rene Angel Silva Zuñiga",
                        email = "reneangel.silva@gmail.com"
                )
        ),
        servers = {
                @Server(
                        description = "DEVELOPER SERVER",
                        url = "http://localhost:8088"
                )
        },
        security = @SecurityRequirement(
                name = "Security Token"
        )
)
@SecurityScheme(
        name = "SecurityToken",
        description = "Access Token",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
