package com.moveapps.users;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Usuarios",
                description = "Documentación para la API Usuarios",
                version = "1.0.0"
        ),
        servers = {
                @Server(url = "http://localhost:8082", description = "Local Server")
        }
)
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

    @Bean
    public GroupedOpenApi yourApi() {
        String[] paths = {"/users/**", "/auth/**"};
        return GroupedOpenApi.builder().group("users").pathsToMatch(paths).build();
    }

}
