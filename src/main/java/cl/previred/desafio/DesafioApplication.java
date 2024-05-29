package cl.previred.desafio;

import cl.previred.desafio.entity.UserEntity;
import cl.previred.desafio.enums.StateTaskEnum;
import cl.previred.desafio.service.StateTaskService;
import cl.previred.desafio.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	/**
	 * Script for creation of dummy users
	 *
	 */
	@Bean
	CommandLineRunner run(UserService userService, StateTaskService stateTaskService){
		return args -> {
			log.info("generando dummy users...");

			UserEntity user1 = userService.createUser(UserEntity.builder()
					.email("jcoltrane@gmail.com")
					.fullname("John Coltrane")
					.password("supreme").build());
			log.info("UUID user 1 " + user1.getId());

			UserEntity user2 = userService.createUser(UserEntity.builder()
					.email("bevans@gmail.com")
					.fullname("Bill Evans")
					.password("undercurrent").build());
			log.info("UUID user 2 " + user2.getId());

			// generate task states
			for (StateTaskEnum st : StateTaskEnum.values()) {
				stateTaskService.createStateTask(st);
			}

		};
	}
}
