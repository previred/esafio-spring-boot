package cl.previred.nuevospa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringdocConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI().info(
            new Info()
                .title("API Gestión de tareas")
                .description("API de gestión de tareas para Previred")
                .contact(new Contact().email("gonzaloleiva01@gmail.com"))
                .version("1.0")
        )
        .addSecurityItem(new SecurityRequirement().addList("Token JWT"))
        .components(new Components()
            .addSecuritySchemes("Token JWT", new SecurityScheme()
                .name("Token JWT")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")));

        return openAPI;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/");
    }
}