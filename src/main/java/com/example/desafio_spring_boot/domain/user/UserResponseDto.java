package com.example.desafio_spring_boot.domain.user;

import java.util.List;

import com.example.desafio_spring_boot.domain.task.TaskResponseDto;

import lombok.Data;

@Data
public class UserResponseDto {
    private final Long id;
    private final String username;
    private final List<TaskResponseDto> tasks;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.tasks = user.getTasks().stream().map(TaskResponseDto::new).toList();
    }

    public UserResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
        this.tasks = List.of();
    }
}
