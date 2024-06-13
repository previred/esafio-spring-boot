package com.example.usertask;

import com.example.usertask.domain.AppUser;
import com.example.usertask.domain.AppUserRepository;
import com.example.usertask.domain.Task;
import com.example.usertask.domain.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class UsertaskApplication  implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(UsertaskApplication.class);

	private final TaskRepository repository;
	private final AppUserRepository urepository;

	public UsertaskApplication(TaskRepository repository, AppUserRepository urepository) {
		this.repository = repository;
		this.urepository = urepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(UsertaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.save(new Task("Repair car engine",  "ADF-1121" ));
		repository.save(new Task("Buy new Nissan's car",   "SSJ-3002" ));
		repository.save(new Task("Sell Toyota's truck", "KKO-0212" ));

		// Username: user, password: user
		urepository.save(new AppUser("user", "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue", "USER"));

		// Username: admin, password: admin
		urepository.save(new AppUser("admin", "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", "ADMIN"));

		// Fetch all cars and log to console
		for (Task task : repository.findAll()) {
			logger.info("task: {}, registration task: {}", task.getTask(), task.getRegistrationNumber());
		}
	}
}
