package com.nuevospa.task.management.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("com.nuevospa.task.management.controller")) 
	            .build()
	            .apiInfo(apiInfo());
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	            .title("API PLATAFORMA GESTION DE TAREAS")
	            .description("Plataforma gestión de tareas")
	            .version("1.0.0")
	            .contact(new Contact("Jorge Rodríguez Novoa", "", "jrodrigueznovoa@gmail.com"))
	            .build();
	    }

}
