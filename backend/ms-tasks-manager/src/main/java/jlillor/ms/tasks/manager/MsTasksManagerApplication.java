package jlillor.ms.tasks.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase de aplicación principal.
 *
 * @version 1.0
 * @since 1.0
 * @author Juan Lillo
 */
@EnableSwagger2
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ConfigurationProperties
@PropertySource("classpath:ms-tasks-manager-1.0.properties")
@PropertySource("classpath:messages.properties")
public class MsTasksManagerApplication {

    /** isEnabled. */
    @Value("${swagger.ui.enabled: false}")
    private boolean isEnabled;
    /** apiName. */
    @Value("${swagger.info.name: Nombre de la API}")
    private String apiName;
    /** apiDescription. */
    @Value("${swagger.info.description: Descripción de la API}")
    private String apiDescription;
    /** apiVersion. */
    @Value("${swagger.info.version: Versión de la API}")
    private String apiVersion;
    /** contactName. */
    @Value("${swagger.info.contact.name: Nombre del líder técnico}")
    private String contactName;
    /** contactEmail. */
    @Value("${swagger.info.contact.mail: contacto@email.com}")
    private String contactEmail;

    // -----------------------------------------------------------------------------------------
    // -- Beans --------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * Bean de configuración de Swagger.
     *
     * @return {@link Docket} configuración de Swagger
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                        .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title(this.apiName)
                        .description(this.apiDescription)
                        .version(this.apiVersion)
                        .contact(new Contact(this.contactName, "", this.contactEmail))
                        .build())
                .enable(this.isEnabled)
                .useDefaultResponseMessages(false);
    }

    /**
     * Bean de encriptación de contraseñas.
     *
     * @return {@link BCryptPasswordEncoder} encriptador de contraseñas
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // -------------------------------------------------------------------------------------------------------
    // -- Main -----------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------
    /**
     * Método principal
     *
     * @param args {@link String[]} Argumentos de entrada
     */
    public static void main(String[] args) {
        SpringApplication.run(MsTasksManagerApplication.class, args);
    }

}