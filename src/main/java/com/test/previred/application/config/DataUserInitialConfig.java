package com.test.previred.application.config;

import com.test.previred.domain.model.user.User;
import com.test.previred.domain.usecase.user.UserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DataUserInitialConfig {

    //clase para crear usuarios de prueba
    private final List<User> users = new ArrayList<>();
    private final UserUseCase userUseCase;

    @PostConstruct
    public void createUsers() {
        users.add(new User("admin", "admin@test.com", "Canaima23."));
        users.forEach(userUseCase::saveUser);
    }


}
