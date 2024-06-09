package com.test.moveapps.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "TASK_STATUS")
public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "task_status_name")
    private String task_status_name;

    @Column(name = "active", nullable = false)
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask_status_name() {
        return task_status_name;
    }

    public void setTask_status_name(String task_status_name) {
        this.task_status_name = task_status_name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
