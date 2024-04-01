package cl.previred.desafip.msprevireddesafio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks_state")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id", nullable = false)
    private Long id;
    private String state;

}
