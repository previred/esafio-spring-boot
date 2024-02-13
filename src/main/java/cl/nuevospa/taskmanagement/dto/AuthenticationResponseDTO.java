package cl.nuevospa.taskmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Objeto de respuesta para autenticación que contiene el token JWT generado.")
public class AuthenticationResponseDTO {
    @Schema(description = "Token JWT generado para el usuario autenticado", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiZXhwIjoxNjE5MjM5MDIyLCJpYXQiOjE2MTkxNTI2MjJ9.dPJ8QEb0t-UyUo8lI8mT5A6TQ5ZyR2dz4Qg_qo0HUqA")
    private String jwt;
}
