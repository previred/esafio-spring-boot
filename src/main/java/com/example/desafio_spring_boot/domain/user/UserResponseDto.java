package com.example.desafio_spring_boot.domain.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private final Long id;
    private final String username;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public UserResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
