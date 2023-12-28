package com.example.gestiontareas.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "estado_tareas")
public class EstadoTarea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado;

    private static final long serialVersionUID = -685575698520440772L;
}
