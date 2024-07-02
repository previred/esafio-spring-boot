package ar.com.challenge.desafio_spring_boot.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Desafio SpringBoot")
                        .description("APP Services Desafio")
                        .contact(new Contact()
                                .name("Ariel Andres Acho")
                                .email("ariel.andres.acho@gmail.com")
                        )
                        .version("0.0.1")
                )
                .addServersItem(new Server().url("http://localhost:8080").description("Dev Local"));
    }
}
