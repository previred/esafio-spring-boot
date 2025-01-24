package cl.previred.tasksapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TASK_STATUS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASK_STATUS_ID")
    private Integer taskStatusId;

    @Column(name = "TASK_STATUS_NAME")
    private String taskStatusName;
}
