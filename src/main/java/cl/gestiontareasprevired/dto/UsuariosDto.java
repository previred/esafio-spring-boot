package cl.gestiontareasprevired.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link cl.gestiontareasprevired.model.Usuarios}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosDto implements Serializable {

    @Size(max = 255)
    String idUser;

    @Size(max = 30)
    String nombreCompleto;

    @Size(max = 30)
    String email;

    @Size(max = 255)
    String password;

    LocalDate fechaCreacion;
}