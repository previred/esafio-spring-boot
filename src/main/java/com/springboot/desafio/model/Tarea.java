package com.springboot.desafio.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    @NotNull
    private Long usuarioId;
    @NotNull
    private Long estadoTareaId;

    public Tarea(String nombre, String descripcion, Integer storyPoint, Long usuarioId, Long estadoTareaId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.storyPoint = storyPoint;
        this.usuarioId = usuarioId;
        this.estadoTareaId = estadoTareaId;
    }
}
