package ar.com.challenge.desafio_spring_boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "SigninDto")
public class SigninDto {
    @Schema(description = "username", example = "AAcho")
    private String username;
    @Schema(description = "Password", example = "********")
    private String password;
}
