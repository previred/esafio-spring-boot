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
public class SignupDto {
    @Schema(description = "Name", example = "Ariel Andres")
    private String name;
    @Schema(description = "Lastname", example = "Acho")
    private String lastname;
    @Schema(description = "Username", example = "ArielAndresAcho@gmail.com")
    private String username;
    @Schema(description = "Password", example = "********")
    private String password;
    @Schema(description = "Email", example = "ArielAndresAcho@gmail.com")
    private String email;
    @Schema(description = "Role", example = "User")
    private String role;
}
