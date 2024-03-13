package cl.gestiontareasprevired.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Respuesta por defecto")
public class DefaultResponse {
    @Schema(description = "Indica si la operación fue exitosa", example = "true")
    private Boolean success;

    @Schema(description = "Mensaje de éxito", example = "Registro guardado correctamente.")
    private String message;
}
