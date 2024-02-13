package cl.nuevospa.taskmanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi taskApi() {
        return GroupedOpenApi.builder()
                .group("tasks")
                .pathsToMatch("/api/v1/task/**")
                .build();
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("authentication")
                .pathsToMatch("/api/v1/auth/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Management API")
                        .version("1.0")
                        .description("API para la gestión de tareas y autenticación de usuarios. " +
                                "Incluye funcionalidades para crear, listar, actualizar, y eliminar tareas, " +
                                "así como autenticar usuarios y generar tokens JWT.")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Jorge Carrenca")
                                .url("https://www.linkedin.com/in/jorge-carrenca-fernández-a72101118")
                                .email("jorgec87@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
