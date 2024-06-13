package com.example.usertask;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI carDatabaseOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Task REST API")
                                 .description("User Task API")
                                 .version("1.0"));
    }
}
