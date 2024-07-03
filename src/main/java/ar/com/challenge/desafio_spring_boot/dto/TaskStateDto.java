package ar.com.challenge.desafio_spring_boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Schema(description = "TaskStateDto")
public class TaskStateDto {
    @Schema(description = "Id del state task", example = "1")
    private int id;
    @Schema(description = "status", example = "VALID")
    @NotNull
    private String status;
}
