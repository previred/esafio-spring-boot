package cl.previred.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String description;

    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDate dueDate;
    
    // Relación con User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // user_id es la FK en la tabla Task
    @Getter
    @Setter
    private User user;

    // Relación con StatusTask
    @ManyToOne
    @JoinColumn(name = "status_task_id", nullable = false) // status_task_id es la FK en la tabla Task
    @Getter
    @Setter    
    private StatusTask statusTask;    
    
}
