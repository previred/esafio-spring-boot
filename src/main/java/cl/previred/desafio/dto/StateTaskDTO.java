package cl.previred.desafio.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateTaskDTO {
    private Long id;
    private String status;
}
