package cl.previred.nuevospa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @Schema(example = "admin", required = true, description = "Username")
    private String username;
    @Schema(example = "cambiame", required = true, description = "Password para loguearse")
    private String password;
}
