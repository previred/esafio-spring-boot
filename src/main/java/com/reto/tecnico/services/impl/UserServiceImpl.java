package com.reto.tecnico.services.impl;

import com.reto.tecnico.exceptions.CustomException;
import com.reto.tecnico.model.User;
import com.reto.tecnico.repository.IUserRepository;
import com.reto.tecnico.services.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("El correo no se encuentra registrado"));
    }

}