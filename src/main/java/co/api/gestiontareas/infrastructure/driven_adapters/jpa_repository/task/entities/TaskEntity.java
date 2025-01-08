package co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.task.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tarea")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long userId;

    private Long status;
}
