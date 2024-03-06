package cl.previred.nuevospa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDto {
    @Schema(example = "1", description = "ID del estado. Valores posibles: 1=Pendiente, 2=En Curso, 3=Terminada")
    private Integer id;
    @Schema(example = "Pendiente", description = "Nombre del Estado", accessMode = AccessMode.READ_ONLY)
    private String nombre;
}
