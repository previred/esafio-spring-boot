package cl.nuevo.spa.taskmanager.domain.dto;

import cl.nuevo.spa.taskmanager.domain.common.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

  @Schema(description = "Task ID")
  private Long id;

  @Schema(description = "Task title", required = true, minLength = 3, maxLength = 180)
  @NotBlank(message = "Title cannot be empty")
  @Size(
      min = 3,
      max = 180,
      message = "Title must have a minimum length of 3 and a maximum length of 180 characters")
  private String title;

  @Schema(description = "Task description", maxLength = 300)
  @NotBlank(message = "Description cannot be empty")
  @Size(max = 300, message = "Description must have a maximum length of 300 characters")
  private String description;

  @Schema(description = "Task status", required = true)
  @NotNull(message = "Status cannot be null")
  private TaskStatus status;
}
