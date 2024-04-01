package cl.previred.desafip.msprevireddesafio.config;

import cl.previred.desafip.msprevireddesafio.entities.TaskState;
import cl.previred.desafip.msprevireddesafio.entities.User;
import cl.previred.desafip.msprevireddesafio.repositories.TaskRepository;
import cl.previred.desafip.msprevireddesafio.repositories.TaskStateRepository;
import cl.previred.desafip.msprevireddesafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class DataPreloader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskStateRepository taskStateRepository;

    @Override
    public void run(String... args) throws Exception {
        preLoadUser();
        preoLoadTaskState();
    }

    private void preLoadUser(){
        User user1 = User.builder()
                .name("Pedro")
                .email("pedro@gmail.com")
                .password("123456")
                .build();

        User user2 = User.builder()
                .name("Juan")
                .email("juan@gmail.com")
                .password("123456")
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
    };
    private void preoLoadTaskState(){
        TaskState taskState1 = TaskState.builder()
                .state("Pendiente")
                .build();

        TaskState taskState2 = TaskState.builder()
                .state("En progreso")
                .build();

        TaskState taskState3 = TaskState.builder()
                .state("Completada")
                .build();
        taskStateRepository.save(taskState1);
        taskStateRepository.save(taskState2);
        taskStateRepository.save(taskState3);

    };
}