package com.nuevoapp.prueba.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "task_status")
public class TaskStatusEntity {

    @Id
    private String status;
    private String description;


}
