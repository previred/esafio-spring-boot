package com.desafio.task.repository;

import com.desafio.task.entity.User;

public interface UserRepository extends IGenericRepository<User, Integer>{
    User findOneByUsername(String username);
}
