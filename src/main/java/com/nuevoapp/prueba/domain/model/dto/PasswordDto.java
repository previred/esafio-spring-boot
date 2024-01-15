package com.nuevoapp.prueba.domain.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PasswordDto {
    private String userEmail;
    private String password;
}
