package com.moveapps.taskmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estados_tarea")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskState extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_id")
    private Long taskId;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @Column(name = "external_id")
    private String externalId;

    public enum TaskStatus {
        CREATED,IN_PROGRESS,DONE
    }
}
