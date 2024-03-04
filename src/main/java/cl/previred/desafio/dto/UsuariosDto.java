package cl.previred.desafio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO de Usuarios")
public class UsuariosDto {
    @Schema(description = "Nombre de usuario", example = "Gabriel")
    private String name;
    @Schema(description = "Apellido de usuario", example = "Rivera")
    private String lastname;
    @Schema(description = "Nombre de cuenta", example = "gaboAdmin")
    private String username;
    @Schema(description = "Email de usuario", example = "gabriel.rivera.gonzalez@gmail.com")
    private String mail;
    @Schema(description = "Rol de usuario", example = "ADMIN")
    private String role;
    @Schema(description = "Token de usuario", example = "x3x3#456")
    private String token;
}
