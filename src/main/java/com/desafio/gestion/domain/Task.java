package com.desafio.gestion.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tareas")
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "descripcion", nullable = false)
    private String description;

    @Column(name = "fecha_limite", nullable = false)
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private TaskStatus status;
}
