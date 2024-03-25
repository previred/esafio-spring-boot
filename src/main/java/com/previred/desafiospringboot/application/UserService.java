package com.previred.desafiospringboot.application;

import com.previred.desafiospringboot.domain.model.User;

public interface UserService {
    User loadUserByUsername(String username) throws Exception;
}
