package com.platform.task.backend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioTecnicoPrevired1Application {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTecnicoPrevired1Application.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("DesafioTecnicoPrevired1Application API").version("2.5.0")
				.description("DesafioTecnicoPrevired1Application Spring Boot 3 with Swagger")
				.termsOfService("http://swagger.io/terms/"));

	}

}
