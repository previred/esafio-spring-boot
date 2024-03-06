package com.spa.crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ESTADOS_TAREA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadosTarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;
    private String nombreEstado;

    @OneToMany(mappedBy = "estadosTarea")
    private Set<Tareas> tareas = new HashSet<>();
}
