package com.spa.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto Estado de Tarea")
public class EstadosTareaDTO {
    @Schema(description = "Id del Estado Tarea", example = "1")
    private Long idEstado;
    @Schema(description = "nombre estado tarea", example = "Completada")
    private String nombreEstado;
}
