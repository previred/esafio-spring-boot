package com.test.previred.infrastructure.adapter.h2.task;

import com.test.previred.infrastructure.adapter.h2.config.BaseEntity;
import com.test.previred.infrastructure.adapter.h2.enums.TaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "task_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskStatusEntity extends BaseEntity {


    private String name;

    public TaskStatusEntity(TaskStatusEnum taskStatus) {
        this.name = taskStatus.name();
    }
}
