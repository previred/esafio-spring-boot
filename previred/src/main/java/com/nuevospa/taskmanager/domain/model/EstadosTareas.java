package com.nuevospa.taskmanager.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "estados_tarea")
public class EstadosTareas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
