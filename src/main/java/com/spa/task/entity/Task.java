package com.spa.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spa.task.audit.Auditable;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tasks")
public class Task extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 5025033176299290970L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task")
    private String task;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_status_id")
    @JsonIgnore
    private TaskStatus status;


}
