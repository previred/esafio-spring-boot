package com.desafio.gestion.service;

import com.desafio.gestion.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    List<User> findAll();
}
