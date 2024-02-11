package com.previred.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)

public class OpenAPIConfiguration {
	 @Bean
	    public OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .components(new Components())
	                .info(new Info()
	                		.title("DESAFIO PREVIRED")
	                		.description("Esta aplicacion ha sido desarrollada en Java utilizando Spring Boot para abordar el desafio de Previred."
	                				+ "\n\nSistema que permite a los usuarios crear, actualizar, eliminar y listar tareas.")
	                		.contact(new Contact()
	                				.name("Sergio Silva Arancibia")
		                			.email("sergio.silva.arancibia@gmail.com")
		                			)
	                		.version("1.0.0")
	                )
	                .addServersItem(new Server().url("http://localhost:8082").description("Ambiente Local"));
	    }
}