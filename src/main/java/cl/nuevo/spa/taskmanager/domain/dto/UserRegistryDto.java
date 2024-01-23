package cl.nuevo.spa.taskmanager.domain.dto;

import cl.nuevo.spa.taskmanager.domain.common.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistryDto {

  @Schema(description = "User's name (minimum 3 characters)")
  private String name;

  @Schema(description = "Username (minimum 3 characters)")
  private String userName;

  @Schema(description = "User's password (minimum 8, maximum 16 characters, alphanumeric)")
  private String password;

  @Schema(
      description =
          "Confirm password (minimum 8, maximum 16 characters, alphanumeric, must match the password)")
  private String confirmPassword;

  @Schema(description = "User roles (at least one role)")
  private Set<UserRole> roles;
}
