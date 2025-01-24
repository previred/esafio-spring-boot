package cl.previred.tasksapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TasksApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApiApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI()
				.info(
						new Info()
								.title("Especificación Api REST - API para gestión de Tareas")
								.version(appVersion)
								.description("Desafío Técnico Previred - API para gestión de Tareas")
								.termsOfService("http://swagger.io/terms")
								.license(new License().name("Apache 2.0").url("http://springdoc.org"))
				);
	}

	@Value( "${prueba.tecnica.previred.version}" )
	private String appVersion;
}
