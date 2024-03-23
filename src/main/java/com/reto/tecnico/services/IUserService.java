package com.reto.tecnico.services;


import com.reto.tecnico.model.User;

public interface IUserService {
    User getUserByUsername(String username);
}