package com.example.gestiontareas.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Ignacio Beltran",
                        email = "ignacio.beltran.silva@gmail.com"
                ),
                description = "Documentacion para gestion de tareas de previred",
                title = "Gestion de tareas previred"
        )
)
public class OpenApiConfig {
}
