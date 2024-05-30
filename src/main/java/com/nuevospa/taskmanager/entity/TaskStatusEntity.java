package com.nuevospa.taskmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_status", schema = "public")
public class TaskStatusEntity {

    @Id
    @Column(name = "task_status_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID taskStatusEntity;
    private String description;
}
