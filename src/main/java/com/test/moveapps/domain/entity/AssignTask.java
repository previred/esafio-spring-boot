package com.test.moveapps.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "ASSIGN_TASK")
public class AssignTask {

    public AssignTask() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_user_assign", nullable = false)
    private Long id_user_assign;

    @Column(name = "id_task_status", nullable = false)
    private Long id_task_status;

    @Column(name = "id_task", nullable = false)
    private Long id_task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_user_assign() {
        return id_user_assign;
    }

    public void setId_user_assign(Long id_user_assign) {
        this.id_user_assign = id_user_assign;
    }

    public Long getId_task_status() {
        return id_task_status;
    }

    public void setId_task_status(Long id_task_status) {
        this.id_task_status = id_task_status;
    }

    public Long getId_task() {
        return id_task;
    }

    public void setId_task(Long id_task) {
        this.id_task = id_task;
    }
}
