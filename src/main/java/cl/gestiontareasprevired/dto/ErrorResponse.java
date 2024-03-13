package cl.gestiontareasprevired.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Respuesta por defecto para errores")
public class ErrorResponse {
    @Schema(description = "Indica si la operación fue exitosa", example = "false")
    private Boolean success;

    @Schema(description = "Mensaje de error", example = "Credenciales inválidas")
    private String message;
}
