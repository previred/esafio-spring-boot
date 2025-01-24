package com.previred.desafio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa una tarea en el sistema")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la tarea", example = "1", required = true)
    private Long id;

    @Schema(description = "Título de la tarea", example = "Configurar servidor", required = true)
    private String titulo;

    @Schema(description = "Descripción detallada de la tarea", example = "Configurar y desplegar el servidor para producción")
    private String descripcion;

    @ManyToOne
    @Schema(description = "Estado actual de la tarea")
    private EstadoTarea estado;

    @ManyToOne
    @JsonBackReference // Evita ciclos de serialización si es necesario
    @Schema(description = "Usuario asignado a la tarea")
    private Usuario usuario;
}
