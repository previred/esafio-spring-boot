package cl.nuevospa.taskmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {


    @Schema(description = "Codigo de respuesta", example = "200")
    private String code;

    @Schema(description = "Fecha de creación", example = "2022-01-01T00:00:00.000Z")
    private String timestamp;

    @Schema(description = "Mensaje de respuesta", example = "Tarea creada exitosamente")
    private String message;



}
