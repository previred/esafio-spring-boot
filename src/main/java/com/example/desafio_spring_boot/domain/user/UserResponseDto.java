package com.example.desafio_spring_boot.domain.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private final Long id;
    private final String username;
}
