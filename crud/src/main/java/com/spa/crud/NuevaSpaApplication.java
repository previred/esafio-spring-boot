package com.spa.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource("classpath:bd.properties")
@EnableJpaRepositories
public class NuevaSpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NuevaSpaApplication.class, args);
	}

}
