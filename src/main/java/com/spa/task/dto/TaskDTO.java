package com.spa.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    @Schema(description = "id de la tarea")
    private Long id;

    @Schema(
            description = "detalle de la tarea",
            example = "modelar la bd",
            required = true
    )
    @NotEmpty
    @Size(min = 2, message = "{task-description-limit}")
    private String task;

    @Schema(description = "estado de la tarea")
    private String status;

    @Schema(
            description = "estado de la tarea 1=TO_DO, 2=IN_PROGRESS, 3=TO_VERIFY, 4=DONE",
            example = "1",
            required = true
    )
    @Min(value = 1, message = "{task-status-values}")
    @Max(value = 4, message = "{task-status-values}")
    private Long statusId;

    @Schema(description = "fecha de creacion de la tarea")
    private String creationDate;

    @Schema(description = "fecha de actualizacion de la tarea")
    private String updatedDate;

    @Schema(
            description = "usuario asignado a la tarea",
            example = "admin"
    )
    private String user;


}
