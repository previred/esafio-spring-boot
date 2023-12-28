package org.tareas.model;

import javax.persistence.*;

@Entity
@Table(name = "STATUS")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "descripcion")
    private String descripcion;

    public Estado() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
