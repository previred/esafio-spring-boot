package com.previred.desafiospringboot.infrastructure.presentation.rest.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    String username;
    String password;
}
