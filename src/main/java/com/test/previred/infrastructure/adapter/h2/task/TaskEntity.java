package com.test.previred.infrastructure.adapter.h2.task;

import com.test.previred.infrastructure.adapter.h2.config.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEntity extends BaseEntity {


    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false, foreignKey = @ForeignKey(name = "FK_TASK_STATUS"))
    private TaskStatusEntity status;

}
