package com.previred.challenge.model;

import com.previred.challenge.enums.TaskStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "task")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "user_id")
    private Integer userId;

}
