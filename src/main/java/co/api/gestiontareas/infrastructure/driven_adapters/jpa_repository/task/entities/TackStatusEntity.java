package co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.task.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "estado_tarea")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TackStatusEntity {
    @Id
    private Long id;
    private String status;
}
