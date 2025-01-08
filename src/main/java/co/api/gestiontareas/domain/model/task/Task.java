package co.api.gestiontareas.domain.model.task;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task{

    private Long id;

    private String title;

    private String description;

    private Long userId;

    private Long status;
}
