package com.previred.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.api.models.Usuarios;
import com.previred.api.services.UsuariosService;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public CommandLineRunner initializeUser(UsuariosService usuariosService,
			BCryptPasswordEncoder passwordEncoder) {
		return args -> {

			Usuarios usuario = new Usuarios();
			usuario.setName("Usuario1");
			usuario.setPassword("Usuario1");
			usuario.setEmail("usuario1@usuario1.cl");
			usuario.setRoles("USER");

			usuariosService.agregarUsuario(usuario);

			Usuarios usuario2 = new Usuarios();
			usuario2.setName("Usuario2");
			usuario2.setPassword("Usuario2");
			usuario2.setEmail("usuario2@usuario2.cl");
			usuario2.setRoles("USER");

			usuariosService.agregarUsuario(usuario2);
		};
	}

}
