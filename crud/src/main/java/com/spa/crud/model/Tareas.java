package com.spa.crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="TAREAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tareas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_tarea")
    private String nombreTarea;
    @Column(name = "numero_tarea")
    private String numeroTarea;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Estado")
    private EstadosTarea estadosTarea;
}
