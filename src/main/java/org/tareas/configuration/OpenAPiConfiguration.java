package org.tareas.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPiConfiguration {

    @Bean
    public OpenAPI myOpenAPI() {


        Info info = new Info()
                .title("Tasks Management API")
                .version("1.0")
                .description("Esta API expone endpoints para un mantenedor de tareas. Los endpoints tienes seguridad JWT por lo cual " +
                        "se debe ejecutar el endpoint /user para generar un nuevo token.");

        return new OpenAPI().info(info);
    }
}
