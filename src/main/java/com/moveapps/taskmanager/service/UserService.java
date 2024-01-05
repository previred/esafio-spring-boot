package com.moveapps.taskmanager.service;

import com.moveapps.taskmanager.common.dto.UserResponseDTO;
import com.moveapps.taskmanager.common.exception.UserNotFoundException;
import com.moveapps.taskmanager.entity.User;
import com.moveapps.taskmanager.repository.UserRepository;
import com.moveapps.taskmanager.security.JwtUtil;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public User findByUser(String user){
        return Optional.ofNullable(userRepository.findByUsername(user))
            .orElseThrow(() -> new UserNotFoundException(String.format("User with username = [%s] not found", user)));
    }

    public UserResponseDTO login(String user){
        return UserResponseDTO.builder()
            .token(jwtUtil.createToken(user))
            .user(user)
            .build();
    }



}
