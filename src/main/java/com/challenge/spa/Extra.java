package com.challenge.spa;

import com.challenge.spa.adapter.out.persistence.statustask.SpringStatusTaskRepository;
import com.challenge.spa.adapter.out.persistence.statustask.StatusTaskEntity;
import com.challenge.spa.adapter.out.persistence.user.SpringUserRepository;
import com.challenge.spa.adapter.out.persistence.user.UserEntity;
import com.challenge.spa.application.shared.Role;
import com.challenge.spa.application.shared.Status;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Extra implements InitializingBean {

    private final SpringStatusTaskRepository statusTaskRepository;
    private final SpringUserRepository springUserRepository;
    private final PasswordEncoder passwordEncoder;

    public Extra(SpringStatusTaskRepository statusTaskRepository, SpringUserRepository springUserRepository, PasswordEncoder passwordEncoder) {
        this.statusTaskRepository = statusTaskRepository;
        this.springUserRepository = springUserRepository;
      this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.statusTaskRepository.save(new StatusTaskEntity(Status.CREATED));
        this.statusTaskRepository.save(new StatusTaskEntity(Status.ATTENDED));

        this.springUserRepository.save(new UserEntity(UUID.randomUUID().toString(),
                "admin", passwordEncoder.encode("admin"), Role.ADMIN));
        this.springUserRepository.save(new UserEntity(UUID.randomUUID().toString(),
                "user1", passwordEncoder.encode("user1"), Role.USER));
    }
}
