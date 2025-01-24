package com.previred.desafio.entity;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa el estado de una tarea en el sistema")
public class EstadoTarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del estado de la tarea", example = "1", required = true)
    private Long id;

    @Schema(description = "Nombre descriptivo del estado de la tarea", example = "Pendiente", required = true)
    private String nombre;
}
