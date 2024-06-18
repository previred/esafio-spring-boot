package com.example.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@Getter
@Setter
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String name;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private List<StateTask> stateTasks;

    public Task() {
    }

    public Task(Long taskId, String name, List<StateTask> stateTasks) {
        this.taskId = taskId;
        this.name = name;
        this.stateTasks = stateTasks;
    }
}
