package com.example.desafio_spring_boot.controller;

import com.example.desafio_spring_boot.domain.user.User;
import com.example.desafio_spring_boot.domain.user.UserResponseDto;
import com.example.desafio_spring_boot.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Gestiona los usuarios")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Lista todos los usuarios")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userService.getAllUsers().stream()
                .map(user -> new UserResponseDto(user))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponseDtos);
    }

    @Operation(summary = "Obtener un usuario por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(new UserResponseDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }
}
