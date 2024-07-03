package ar.com.challenge.desafio_spring_boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "SigninDto")
public class SigninDto {

    @Schema(description = "username", example = "aariel")
    @NotNull
    private String username;

    @Schema(description = "Password", example = "1234")
    @NotNull
    private String password;
}
