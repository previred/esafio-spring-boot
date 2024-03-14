package com.desafio.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotNull
    @NotBlank(message = "username no puede estar vacío")
    private String username;

    @NotNull
    @NotBlank(message = "password no puede estar vacío")
    private String password;
}
