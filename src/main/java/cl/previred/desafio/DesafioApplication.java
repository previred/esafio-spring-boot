package cl.previred.desafio;

import cl.previred.desafio.entity.RoleEntity;
import cl.previred.desafio.entity.UserEntity;
import cl.previred.desafio.enums.RoleEnum;
import cl.previred.desafio.enums.StateTaskEnum;
import cl.previred.desafio.service.RoleService;
import cl.previred.desafio.service.StateTaskService;
import cl.previred.desafio.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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
	CommandLineRunner run(UserService userService, StateTaskService stateTaskService, RoleService roleService, PasswordEncoder passwordEncoder){
		return args -> {
			log.info("generando dummy users...");

			roleService.saveRole(RoleEntity.builder().name(RoleEnum.ADMIN).build());
			roleService.saveRole(RoleEntity.builder().name(RoleEnum.USER).build());

			Optional<RoleEntity> optRoleUsr = roleService.findByName(RoleEnum.USER);
			Optional<RoleEntity> optRoleAdm = roleService.findByName(RoleEnum.ADMIN);

			UserEntity user1 = userService.createUser(UserEntity.builder()
					.email("jcoltrane@gmail.com")
					.fullname("John Coltrane")
					.role(optRoleAdm.get())
					.password(passwordEncoder.encode("supreme"))
					.build());
			log.info("UUID user 1 " + user1.getId());

			UserEntity user2 = userService.createUser(UserEntity.builder()
					.email("bevans@gmail.com")
					.fullname("Bill Evans")
					.role(optRoleUsr.get())
					.password(passwordEncoder.encode("undercurrent"))
					.build());
			log.info("UUID user 2 " + user2.getId());

			// generate task states
			for (StateTaskEnum st : StateTaskEnum.values()) {
				stateTaskService.createStateTask(st);
			}

		};
	}
}
