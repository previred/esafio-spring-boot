package com.desafio.gestion.service.impl;

import com.desafio.gestion.domain.User;
import com.desafio.gestion.dto.UserDTO;
import com.desafio.gestion.repository.UserRepository;
import com.desafio.gestion.service.UserService;
import com.desafio.gestion.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID: " + username));
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToUserDtos(users);
    }
}