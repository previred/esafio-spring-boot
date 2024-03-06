package com.spa.crud.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components())
                .info(new Info()
                        .title("PREVIRED Nueva SPA")
                        .description("Aplicacion que permite a los usuarios crear, actualizar, eliminar y listar tareas necesarios para el cliente" +
                                "NuevaSPA.")
                        .contact(new Contact().name("Gonzalo Quintana").email("quintana.gonzalo14@gmail.com"))
                        .version("1.0.0")
                )
                .addServersItem(new Server().url("http://localhost:9090").description("Local"));
    }
}
