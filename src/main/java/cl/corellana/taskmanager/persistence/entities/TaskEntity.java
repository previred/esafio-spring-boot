package cl.corellana.taskmanager.persistence.entities;

import cl.corellana.taskmanager.api.model.TaskStatus;
import lombok.*;

import javax.persistence.*;

@Entity(name = "task")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id = null;

    @Column
    private String title = null;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status", referencedColumnName = "id")
    private TaskStatusEntity status = null;

}
