package cl.tecnova.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateTareaRequest {

    @Schema(name = "nombre", example = "tareaEjemplo")
    private String nombre;

    @Schema(name = "idEstado", example = "1")
    private int idEstado;

}
