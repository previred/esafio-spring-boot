package com.nuevospa.taskmanager.service.impl;

import com.nuevospa.taskmanager.dto.User;
import com.nuevospa.taskmanager.service.UserService;
import org.springframework.stereotype.Service;
import com.nuevospa.taskmanager.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


}
