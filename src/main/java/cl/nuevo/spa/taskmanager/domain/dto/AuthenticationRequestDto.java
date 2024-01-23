package cl.nuevo.spa.taskmanager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticationRequestDto {

  @Schema(description = "Username", example = "wilbert.acosta", required = true)
  @NotBlank(message = "Username cannot be empty")
  @NotNull(message = "Username cannot be null")
  private String userName;

  @Schema(description = "User password", example = "my_dummy_password", required = true)
  @NotBlank(message = "Password cannot be empty")
  @NotNull(message = "Password cannot be null")
  private String password;
}
