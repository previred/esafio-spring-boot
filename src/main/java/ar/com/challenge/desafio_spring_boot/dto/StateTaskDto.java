package ar.com.challenge.desafio_spring_boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "StateTaskDto")
public class StateTaskDto {
    @Schema(description = "Id del state task", example = "1")
    private int id;
    @Schema(description = "status", example = "VALID")
    private String status;
}
