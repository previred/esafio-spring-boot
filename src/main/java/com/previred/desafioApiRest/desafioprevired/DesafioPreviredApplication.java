package com.previred.desafioApiRest.desafioprevired;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioPreviredApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPreviredApplication.class, args);
	}


	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("PreviredDesafioApplication Spring Boot API")
						.version("2.5.0")
						.description("PreviredDesafioApplication Spring Boot 3 with Swagger")
						.termsOfService("http://swagger.io/terms/"));

	}

}
