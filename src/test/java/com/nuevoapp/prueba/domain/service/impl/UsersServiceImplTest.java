package com.nuevoapp.prueba.domain.service.impl;

import com.nuevoapp.prueba.domain.model.dto.UserDto;
import com.nuevoapp.prueba.domain.model.dto.login.LoginRequest;
import com.nuevoapp.prueba.domain.model.dto.login.LoginResponse;
import com.nuevoapp.prueba.domain.model.entity.UserEntity;
import com.nuevoapp.prueba.domain.model.enums.UserStatusEnum;
import com.nuevoapp.prueba.domain.model.mappers.UserMapper;
import com.nuevoapp.prueba.domain.repository.UsersRepository;
import com.nuevoapp.prueba.security.JwtHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private JwtHandler jwtHandler;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UsersServiceImpl usersService;

    @Test
    void testGetUserByEmail_Success() {
        String userEmail = "test@example.com";
        UserEntity userEntity = new UserEntity();
        UserDto userDto = new UserDto();

        when(usersRepository.findById(userEmail))
                .thenReturn(Optional.of(userEntity));

        when(userMapper.toDto(userEntity))
                .thenReturn(userDto);

        UserDto result = usersService.getUserByEmail(userEmail);

        assertNotNull(result);
        assertEquals(userDto, result);
    }

    @Test
    void testGetUserByEmail_UserNotFound() {
        String userEmail = "nonexistent@example.com";

        when(usersRepository.findById(userEmail))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> usersService.getUserByEmail(userEmail));
    }

    @Test
    void testLoginUser_Success() {
        // Mock data
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");

        doNothing().when(usersRepository).updateUserStatusAndLastLoginDateByEmail(
                any(), any(), anyString());

        when(jwtHandler.generateJwtToken(loginRequest.getEmail()))
                .thenReturn("mockedJwtToken");

        ResponseEntity<LoginResponse> result = usersService.loginUser(loginRequest);

        assertNotNull(result);
        assertEquals(UserStatusEnum.ACTIVE, result.getBody().getStatus());
        assertNotNull(result.getBody().getLoginDate());
        assertEquals("mockedJwtToken", result.getBody().getToken());
    }
}
