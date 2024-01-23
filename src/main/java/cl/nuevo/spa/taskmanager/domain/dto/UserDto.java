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
public class UserDto {

  @Schema(description = "User ID")
  private Long id;

  @Schema(description = "User name")
  private String name;

  @Schema(description = "Username")
  private String userName;

  @Schema(description = "User roles")
  private Set<UserRole> roles;

  @Schema(description = "JWT token")
  private String jwt;
}
