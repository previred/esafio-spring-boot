package co.api.gestiontareas.domain.model.task;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TackStatus {
    private Long id;
    private String status;
}
