package com.example.demo;

import com.example.demo.model.entity.TaskStatus;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.repository.TaskStatusRepository;
import com.example.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	UserRepository oUserRepository;
	@Autowired
	TaskStatusRepository oTaskStatusRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return args -> {
			UserEntity oUsuario = UserEntity.builder()
					.username("admin")
					.email("admin@gmail.com")
					.password(new BCryptPasswordEncoder().encode("123456"))
					.build();
			oUserRepository.save(oUsuario);


			Set<TaskStatus> oTaskStatus = new HashSet<>();

			oTaskStatus.add(TaskStatus.builder()
					.statusCode("BR")
					.statusName("Borrador")
					.build());

			oTaskStatus.add(TaskStatus.builder()
					.statusCode("TR")
					.statusName("A tratar")
					.build());

			oTaskStatus.add(TaskStatus.builder()
					.statusCode("EC")
					.statusName("En curso")
					.build());

			oTaskStatus.add(TaskStatus.builder()
					.statusCode("CO")
					.statusName("Completado")
					.build());

			oTaskStatusRepository.saveAll(oTaskStatus);

		};
	}

}
