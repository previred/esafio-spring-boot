package cl.gestiontareasprevired.dto;

import cl.gestiontareasprevired.dto.EstadosTareaDto;
import cl.gestiontareasprevired.dto.UsuariosDto;
import cl.gestiontareasprevired.model.Tarea;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link Tarea}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TareaDto implements Serializable {

    @Size(max = 50)
    private String idTarea;

    @Size(max = 50)
    private String tituloTarea;

    @Size(max = 1000)
    private String descripcionTarea;

    @NotNull
    private String usuarioEmail;

    private LocalDateTime fechaIngreso;

    private LocalDateTime fechaUltimaMod;

    private LocalDateTime fechaFin;

    @NotNull
    private String estadoTarea;
}