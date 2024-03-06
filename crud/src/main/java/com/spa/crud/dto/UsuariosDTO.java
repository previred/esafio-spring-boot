package com.spa.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Dto de Usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosDTO {
    @Schema(description = "Nombre de usuario", example = "Jose")
    private String nombre;
    @Schema(description = "Nombre de cuenta", example = "jp23112")
    private String username;
    @Schema(description = "Email de usuario", example = "jperez@nuevaspa.com")
    private String mail;
    @Schema(description = "Rol de usuario", example = "ADMIN")
    private RolesDTO role;
    @Schema(description = "Token de usuario", example = "eyJhbGciOiJIUzI1NiJ9")
    private String token;
}
