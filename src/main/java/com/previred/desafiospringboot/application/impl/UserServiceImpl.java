package com.previred.desafiospringboot.application.impl;

import com.previred.desafiospringboot.domain.model.User;
import com.previred.desafiospringboot.application.UserService;
import com.previred.desafiospringboot.infrastructure.persistance.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws Exception {
        Optional<User> user = userRepository.findByUsername(username).stream().findFirst();
        return user.orElse(null);
    }


}
