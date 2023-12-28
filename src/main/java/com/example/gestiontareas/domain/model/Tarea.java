package com.example.gestiontareas.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tareas")
@Data
public class Tarea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime asigned;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime finished;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoTarea estadoTarea;

    private static final long serialVersionUID = -6111872362011883955L;

}
