package com.desafio.spring.service.impl;

import com.desafio.spring.error.NotFoundException;
import com.desafio.spring.repository.UserRepository;
import com.desafio.spring.service.IUserService;
import com.desafio.spring.repository.dao.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;

    private UserServiceImpl( UserRepository userRepository){
        this.repository = userRepository;
    }
    @Override
    public User getByEmail(String email) {
        return this.repository.findByEmail(email).get();
    }

    @Override
    public User getByName(String name) {
        User user = this.repository.findByName(name);
        if(user == null){
            throw new NotFoundException("User not found");
        }
        return user;
    }
}
