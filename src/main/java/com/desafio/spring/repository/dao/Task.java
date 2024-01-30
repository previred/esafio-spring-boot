package com.desafio.spring.repository.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;


    @Column(name = "name_task")
    private String nameTask;

    @Column(name = "description_task")
    private String descriptionTask;

    @Column(name = "task_priority")
    private String taskPriority;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_status_task", referencedColumnName = "id",  updatable = false)
    private TaskStatus statusTask;

    @PrePersist
    private void prePersist(){
        if(StringUtils.isEmpty(this.id)){ this.setId(UUID.randomUUID().toString()); }
    }
}
