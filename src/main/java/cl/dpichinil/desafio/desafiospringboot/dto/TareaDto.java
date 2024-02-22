package cl.dpichinil.desafio.desafiospringboot.dto;

import cl.dpichinil.desafio.desafiospringboot.persistence.entity.EstadoTarea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaDto {
    private int id;
    private String descripcion;
    private boolean activo;
    private EstadoTareaDto estadoTarea;
}
