package cl.gestiontareasprevired.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link cl.gestiontareasprevired.model.EstadosTarea}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadosTareaDto implements Serializable {
    @Size(max = 10)
    String estadoTarea;
    @Size(max = 10)
    String descripcionEstado;
}