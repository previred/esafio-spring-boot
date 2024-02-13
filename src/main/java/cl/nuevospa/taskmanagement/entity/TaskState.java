package cl.nuevospa.taskmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "task_states")
public class TaskState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Relación inversa (Opcional, para tener acceso a las tareas desde el estado)
    @OneToMany(mappedBy = "taskState")
    private Set<Task> tasks;
}
