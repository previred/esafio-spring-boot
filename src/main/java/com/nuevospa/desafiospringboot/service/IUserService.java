package com.nuevospa.desafiospringboot.service;

import com.nuevospa.desafiospringboot.dto.UserDTO;
import com.nuevospa.desafiospringboot.model.User;

public interface IUserService {
    public UserDTO save(User user);
}
