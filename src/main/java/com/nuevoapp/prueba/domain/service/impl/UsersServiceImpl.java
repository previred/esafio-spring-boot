package com.nuevoapp.prueba.domain.service.impl;

import com.nuevoapp.prueba.domain.model.dto.UserDto;
import com.nuevoapp.prueba.domain.model.dto.login.LoginRequest;
import com.nuevoapp.prueba.domain.model.dto.login.LoginResponse;
import com.nuevoapp.prueba.domain.model.entity.UserEntity;
import com.nuevoapp.prueba.domain.model.enums.UserStatusEnum;
import com.nuevoapp.prueba.domain.model.mappers.UserMapper;
import com.nuevoapp.prueba.domain.repository.UsersRepository;
import com.nuevoapp.prueba.domain.service.PasswordService;
import com.nuevoapp.prueba.domain.service.UsersService;
import com.nuevoapp.prueba.security.JwtHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final JwtHandler jwtHandler;
    private final UserMapper userMapper;
    @Override
    public UserDto getUserByEmail(String email){

        Optional<UserEntity> optUser = usersRepository.findById(email);
        //basically, if present, map the user, else return 204
        return optUser.map(userMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Invalid username"));
    }

    @Override
    public ResponseEntity<LoginResponse> loginUser(LoginRequest request){

        usersRepository.updateUserStatusAndLastLoginDateByEmail(UserStatusEnum.ACTIVE, ZonedDateTime.now(), request.getEmail());
        return ResponseEntity.ok(LoginResponse.builder()
                .loginDate(ZonedDateTime.now())
                .status(UserStatusEnum.ACTIVE)
                .token(jwtHandler.generateJwtToken(request.getEmail()))
                .build());
    }
}
