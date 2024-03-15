package com.nuevospa.desafiospringboot.security;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;

}
