package cl.previred.desafio.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String email;
    private String fullname;
    private String password;
}
