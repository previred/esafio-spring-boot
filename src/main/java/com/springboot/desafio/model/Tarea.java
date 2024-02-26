package com.springboot.desafio.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombre;
    private String descripcion;
    private Integer storyPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @NotNull
    @NotNull
    private User usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_tarea_id")
    @NotNull
    private EstadoTarea estadoTarea;

    public Tarea(String nombre, String descripcion, Integer storyPoint, User usuario, EstadoTarea estadoTarea) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.storyPoint = storyPoint;
        this.usuario = usuario;
        this.estadoTarea = estadoTarea;
    }
}
