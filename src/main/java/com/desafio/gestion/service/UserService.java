package com.desafio.gestion.service;

import com.desafio.gestion.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findByUsername(String username);

    List<UserDTO> findAll();
}
