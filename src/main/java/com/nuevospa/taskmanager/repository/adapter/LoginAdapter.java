package com.nuevospa.taskmanager.repository.adapter;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.nuevospa.taskmanager.dto.*;
import com.nuevospa.taskmanager.repository.LoginRepository;
import com.nuevospa.taskmanager.repository.jpa.LoginEntityRepository;
import com.nuevospa.taskmanager.util.MapperUtil;


import java.util.Optional;

@Repository
public class LoginAdapter implements LoginRepository {

    private final LoginEntityRepository loginEntityRepository;

    public LoginAdapter(LoginEntityRepository loginEntityRepository) {
        this.loginEntityRepository = loginEntityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserLogin> findByEmailAndPassword(Login login) {
        return loginEntityRepository.findByEmailAndPassword(login.email(), login.password())
                .map(MapperUtil::entityToUserLoginDto);
    }
}
