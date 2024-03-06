package com.spa.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto de Tareas")
public class TareasDTO {
    @Schema(description = "Id de Tarea", example = "1")
    private Long id;
    @Schema(description = "Nombre de la Tarea", example = "Crear WS")
    private String nombreTarea;
    @Schema(description = "Numero de la Tarea", example = "12321")
    private String numeroTarea;
    private EstadosTareaDTO estadosTarea;
}
