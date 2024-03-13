package cl.gestiontareasprevired.dto;

import cl.gestiontareasprevired.model.Tarea;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Tarea}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ingreso de Tareas")
public class TareaReqDto implements Serializable {

    @NotNull
    @Size(max = 50)
    @Schema(description = "Titulo de Tarea", example = "Realizar Reunion Semanal")
    String tituloTarea;

    @NotNull
    @Size(max = 1000)
    @Schema(description = "descripcion tarea", example = "Temas a tratar en la reunion semanal: ...")
    String descripcionTarea;

    @NotNull
    @Schema(description = "email usuario", example = "usuario1@example.com")
    String username;

    @NotNull
    @Schema(description = "Codigo Estado tarea", example = "INI")
    String estado;
}