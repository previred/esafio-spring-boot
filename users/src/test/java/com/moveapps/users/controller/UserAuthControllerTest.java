package com.moveapps.users.controller;

import com.moveapps.users.dto.RegisterUserDto;
import com.moveapps.users.model.User;
import com.moveapps.users.service.JwtService;
import com.moveapps.users.service.UserAuthService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class UserAuthControllerTest {

    @Mock
    private UserAuthService userAuthService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserAuthController userAuthController;


    @Test
    void register() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        User registeredUser = new User();
        when(userAuthService.signup(any(RegisterUserDto.class))).thenReturn(registeredUser);

        ResponseEntity<User> responseEntity = userAuthController.register(registerUserDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(registeredUser, responseEntity.getBody());
    }

    @Test
    void authenticate() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        User registeredUser = new User();
        when(userAuthService.signup(any(RegisterUserDto.class))).thenReturn(registeredUser);

        ResponseEntity<User> responseEntity = userAuthController.register(registerUserDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(registeredUser, responseEntity.getBody());
    }
}