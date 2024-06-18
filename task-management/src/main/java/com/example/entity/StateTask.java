package com.example.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "state_task")
@Getter
@Setter
@Builder
public class StateTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateTaskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskId")
    private Task task;

    private String name;

    public StateTask() {
    }

    public StateTask(Long stateTaskId, Task task, String name) {
        this.stateTaskId = stateTaskId;
        this.task = task;
        this.name = name;
    }
}
