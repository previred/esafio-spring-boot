package com.desafio.desafiospringboot.model.configurations;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;

@Configuration
public class Configuracion {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestión de Tareas con Spring Boot y Java")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                        .servers(List.of(new Server().url("http://localhost:8080")));
    }*/
    /*@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }*/

    // OpenApi
    @Bean
    public OpenAPI customOpenAPI() {
        Yaml yaml = new Yaml();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("openapi.yaml")) {
            OpenAPI openAPI = yaml.loadAs(in, OpenAPI.class);
            //openAPI.setInfo(new Info().title("API de ejemplo").version("1.0.0"));
            //openAPI.setServers(List.of(new Server().url("http://localhost:8080")));
            return openAPI;
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar openapi.yaml", e);
        }
    }

    /*@Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> openApi.setInfo(new Info().title("Gestión de Tareas con Spring Boot y Java").version("1.0.0"));
    }*/

    @Bean
    public SwaggerUiConfigParameters swaggerUiConfigParameters(SwaggerUiConfigProperties properties) {
        SwaggerUiConfigParameters parameters = new SwaggerUiConfigParameters(properties);
        parameters.setConfigUrl("/v3/api-docs.yaml");
        return parameters;
    }

}


