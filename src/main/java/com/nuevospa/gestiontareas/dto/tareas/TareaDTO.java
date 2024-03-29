package com.nuevospa.gestiontareas.dto.tareas;

import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TareaDTO {
    private Long id;
    private String descripcion;
    private UsuarioDTO usuario;
    private EstadoTareaDTO estadoTarea;
}
