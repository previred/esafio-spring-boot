package cl.nuevospa.taskmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Objeto de solicitud para autenticación que contiene el nombre de usuario y la contraseña.")
public class AuthenticationRequestDTO {

    @NotBlank(message = "El username es requerido")
    @NotNull(message = "El username es requerido")
    @Schema(description = "Nombre de usuario utilizado para la autenticación", example = "user1", required = true)
    private String username;

    @NotBlank(message = "El password es requerido")
    @NotNull(message = "El password es requerido")
    @Schema(description = "Contraseña del usuario para la autenticación", example = "1234", required = true)
    private String password;
}
