package com.moveapps.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {

    @NotNull(message = "Email debe estar diligenciado")
    private String email;

    @NotNull(message = "Contraseña debe estar diligenciado")
    private String password;
}
