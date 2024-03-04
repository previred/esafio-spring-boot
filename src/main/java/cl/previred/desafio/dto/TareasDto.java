package cl.previred.desafio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto de Tareas")
public class TareasDto{
    @Schema(description = "Id de Tarea", example = "1")
    private int id;
    @Schema(description = "Nombre de la Tarea", example = "Tarea 1")
    private String nameTask;
    private EstadosTareaDto statusTask;
}
