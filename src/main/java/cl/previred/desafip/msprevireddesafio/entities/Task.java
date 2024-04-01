package cl.previred.desafip.msprevireddesafio.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "state_id")
    private TaskState state;
}
