package com.nuevoapp.prueba.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class TasksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status", referencedColumnName = "status")
    private TaskStatusEntity status;

    private String comment;

    @Column(name = "assigned_date")
    private ZonedDateTime assignedDate;

    @Column(name = "init_date")
    private ZonedDateTime initDate;

    @Column(name = "blocked_date")
    private ZonedDateTime blockedDate;

    @Column(name = "completed_date")
    private ZonedDateTime completedDate;

    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;

}
