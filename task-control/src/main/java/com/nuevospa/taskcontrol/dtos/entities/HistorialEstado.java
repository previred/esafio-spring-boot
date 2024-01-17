package com.nuevospa.taskcontrol.dtos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_estados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "tarea" })
public class HistorialEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private long idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_tarea")
    @JsonBackReference
    @Schema(hidden = true)
    private Tarea tarea;

    @ManyToOne
    @JoinColumn(name = "id_estado_tarea")
    @JsonBackReference
    private EstadoTarea estadoTarea;

    @Column(name = "fecha_inicio_estado")
    private LocalDateTime fechaInicioEstado;

    @Column(name = "fecha_fin_estado")
    private LocalDateTime fechaFinEstado;
}
