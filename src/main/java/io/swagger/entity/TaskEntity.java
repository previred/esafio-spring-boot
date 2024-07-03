package io.swagger.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tareas")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "titulo")
    private String title;
    @Column(name = "descripcion")
    private String description;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    private TaskStatusEntity status;
}
