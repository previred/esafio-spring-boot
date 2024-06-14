package com.desafio.spring.ec.ds.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idTasks;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;


}
