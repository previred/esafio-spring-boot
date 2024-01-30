package com.desafio.spring.service;

import com.desafio.spring.repository.dao.User;
public interface IUserService {
    User getByEmail(String email);
    User getByName(String name);
}
