package cl.previred.nuevospa.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TareaDto {

    @Schema(example = "1", description = "ID de la tarea", accessMode = AccessMode.READ_ONLY)
    private Long id;

    @Schema(example = "Titulo de prueba", description = "Titulo de la tarea")
    @NotBlank(message = "titulo obligatorio")
    @Length(max = 250, message = "supera el maximo permitido")
    private String titulo;

    @Schema(example = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto.", description = "Descripción de la tarea")
    @NotBlank(message = "descripcion obligatorio")
    private String descripcion;

    
    @Schema(example = "2024-03-05T23:49:29.095Z", description = "Fecha de creación de la tarea (Manejada internamente)", accessMode = AccessMode.READ_ONLY)
    private Date fechaCreacion;
    @Schema(example = "2024-03-05T23:49:29.095Z", description = "Fecha de creación de la tarea (Manejada internamente)", accessMode = AccessMode.READ_ONLY)
    private Date fechaActualizacion;

    private EstadoDto estado;

    @Schema(accessMode = AccessMode.READ_ONLY)
    private UsuarioDto usuario;
  
}
