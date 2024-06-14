package com.desafio.spring.ec.ds.request;

import com.desafio.spring.ec.ds.entity.Role;

import java.util.List;

public record UserRegister(
        String id,
        String name,
        String lastname,
        String email,
        String username,
        String password,
        List<Role> roles
) {
}
