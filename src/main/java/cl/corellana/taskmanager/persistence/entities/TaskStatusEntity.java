package cl.corellana.taskmanager.persistence.entities;

import cl.corellana.taskmanager.api.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "task_status")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id = null;
    @Column
    private String status;

    public static TaskStatusEntity of(TaskStatus statusEnum){
        return TaskStatusEntity.builder()
                .id(statusEnum.getId())
                .status(statusEnum.name())
                .build();
    }

}
