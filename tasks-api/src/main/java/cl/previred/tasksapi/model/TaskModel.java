package cl.previred.tasksapi.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TASK")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASK_ID")
    private Integer taskId;
    @Column(name = "TASK_NAME")
    private String taskName;
    @Column(name = "TASK_DESCRIPTION")
    private String taskDescription;
    @ManyToOne
    @JoinColumn(name = "TASK_STATUS_ID")
    private TaskStatusModel taskStatus;
    // private int taskStatusId;
    // private String taskStatusName;
}
