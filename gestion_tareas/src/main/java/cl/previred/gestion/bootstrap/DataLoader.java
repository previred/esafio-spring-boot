package cl.previred.gestion.bootstrap;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cl.previred.gestion.model.TaskStatus;
import cl.previred.gestion.model.User;
import cl.previred.gestion.repository.TaskStatusRepository;
import cl.previred.gestion.repository.UserRepository;


@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, TaskStatusRepository taskStatusRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.passwordEncoder = passwordEncoder;
    }
 
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            userRepository.save(new User("admin", passwordEncoder.encode("Secret1234"), "admin@gestion.com"));
        }
    
        if (taskStatusRepository.count() == 0) {
            taskStatusRepository.saveAll(List.of(
                    new TaskStatus("Pendiente"),
                    new TaskStatus("En Progreso"),
                    new TaskStatus("Completada")
            ));
        }
    }
    
}


