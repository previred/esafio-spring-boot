package com.nuevospa.tareas.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Usuario {

    @NotBlank
    private String nombre;

    @Email(message = "Debe ser un email válido")
    private String email;

    @NotBlank
    private String password;
}
