package cl.previred.desafio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto Estado de Tarea")
public class EstadosTareaDto{
    @Schema(description = "Id del Estado Tarea", example = "1")
    private int id;
    @Schema(description = "Id de Tarea", example = "VALID")
    private String status;
}
