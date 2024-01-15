package com.nuevoapp.prueba.domain.service.impl;

import com.nuevoapp.prueba.domain.model.entity.PasswordEntity;
import com.nuevoapp.prueba.domain.repository.PasswordRepository;
import com.nuevoapp.prueba.domain.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final PasswordRepository passwordRepository;
    @Override
    public String getUserPassword(String email){
        Optional<PasswordEntity> optPassword = passwordRepository.findById(email);
        return optPassword.map(PasswordEntity::getPassword).orElseThrow(() -> new NoSuchElementException("No elements found"));
    }
}
