package cl.previred.desafio.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private UUID id;
    private UserResponseDTO user;
    private StateTaskDTO status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
