package com.previred.desafio_spring_boot.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "estados_tarea")
public class EstadoTarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstadoTarea{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
