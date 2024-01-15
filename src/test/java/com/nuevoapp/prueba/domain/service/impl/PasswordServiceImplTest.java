package com.nuevoapp.prueba.domain.service.impl;

import com.nuevoapp.prueba.domain.model.entity.PasswordEntity;
import com.nuevoapp.prueba.domain.repository.PasswordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordServiceImplTest {

    @InjectMocks
    private PasswordServiceImpl passwordService;
    @Mock
    private PasswordRepository passwordRepository;



    @Test
    void testGetUserPassword_Success() {
        String userEmail = "test@example.com";
        PasswordEntity passwordEntity = new PasswordEntity();
        passwordEntity.setUserEmail(userEmail);
        passwordEntity.setPassword("hashedPassword");

        when(passwordRepository.findById(userEmail))
                .thenReturn(Optional.of(passwordEntity));

        String result = passwordService.getUserPassword(userEmail);

        Assertions.assertEquals("hashedPassword", result);
    }

    @Test
    void testGetUserPassword_UserNotFound() {
        String userEmail = "nonexistent@example.com";

        when(passwordRepository.findById(userEmail))
                .thenReturn(Optional.empty());

        // Perform the actual test and assert NoSuchElementException
        Assertions.assertThrows(NoSuchElementException.class, () -> passwordService.getUserPassword(userEmail));
    }
}
