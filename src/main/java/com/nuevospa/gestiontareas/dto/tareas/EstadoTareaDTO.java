package com.nuevospa.gestiontareas.dto.tareas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTareaDTO {
    private Long id;
    private String nombre;
}
