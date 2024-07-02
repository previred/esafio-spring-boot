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
@Schema(description = "TaskDto")
public class TaskDto {
    @Schema(description = "Id de task", example = "1")
    private int id;
    @Schema(description = "Name task", example = "Task 1")
    private String name;
    private StateTaskDto status;
}
