package com.spa.task.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API challenge Spring Boot manages task")
                        .version("1.0")
                        .description("API to manage task by users"))
                .externalDocs(new ExternalDocumentation()
                        .description("More about the API"));
    }
}
