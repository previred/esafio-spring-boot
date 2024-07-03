package io.swagger.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "estados_tarea")
public class TaskStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "descripcion")
    private String description;
}
