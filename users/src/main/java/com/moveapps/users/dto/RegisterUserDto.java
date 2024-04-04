package com.moveapps.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    @NotNull(message = "Email debe estar diligenciado")
    private String email;

    @NotNull(message = "Contraseña debe estar diligenciado")
    private String password;

    @NotNull(message = "Nombre debe estar diligenciado")
    private String name;

    @NotNull(message = "Apellido debe estar diligenciado")
    private String lastName;

    @NotNull(message = "Direccion debe estar diligenciado")
    private String address;
}
