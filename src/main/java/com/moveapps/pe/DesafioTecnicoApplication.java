package com.moveapps.pe;
 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;



@SpringBootApplication
public class DesafioTecnicoApplication {

	public static void main(String[] args) {
	SpringApplication.run(DesafioTecnicoApplication.class, args);
		  /*SpringApplication app = new SpringApplication(DesafioTecnicoApplication.class);
	        app.setDefaultProperties(Collections.singletonMap("server.port", "9090"));
	        app.run(args);*/
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
