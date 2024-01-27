package com.enterprise.move.apps.MoveApps.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
public class TaskModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "name_task")
    private String nameTask;
    @Column(name = "description_task")
    private String descriptionTask;

    @Column(name = "task_priority")
    private String taskPriority;

    @ManyToOne
    @JoinColumn(name = "id_responsible", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_status_task", referencedColumnName = "id")
    private StatusTask statusTask;


}
