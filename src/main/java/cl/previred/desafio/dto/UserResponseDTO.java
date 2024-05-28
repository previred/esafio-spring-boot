package cl.previred.desafio.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String email;
    private String fullname;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
