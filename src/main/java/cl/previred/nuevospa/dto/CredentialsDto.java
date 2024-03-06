package cl.previred.nuevospa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDto {
    @Schema(description = "Token JWT con el cual se puede acceder a los endpoints protegidos")
    private String token;
}
