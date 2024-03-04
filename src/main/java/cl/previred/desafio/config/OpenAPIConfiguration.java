package cl.previred.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)

public class OpenAPIConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Desafio SpringBoot Previred")
                        .description("APP Services Desafio Previred")
                        .contact(new Contact()
                                .name("Gabriel Francisco Rivera Gonzalez")
                                .email("gabriel.rivera.gonzalez@gmail.com")
                        )
                        .version("0.0.1")
                )
                .addServersItem(new Server().url("http://localhost:8082").description("Dev Local environment"));
    }
}