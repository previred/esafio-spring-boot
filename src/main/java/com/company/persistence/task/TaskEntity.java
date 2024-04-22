package com.company.persistence.task;


import com.company.persistence.task.status.TaskStatusEntity;
import com.company.persistence.users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "task", schema = "public")
public class TaskEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
    @JoinColumn(name = "task_status_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatusEntity taskStatus;

}
