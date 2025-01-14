package com.previred.gestion_tareas_api.persistence.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.previred.gestion_tareas_api.entities.User;
import com.previred.gestion_tareas_api.persistence.UserDAO;
import com.previred.gestion_tareas_api.repositories.UserRepository;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String name) {

        return userRepository.findByUsername(name);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

}
