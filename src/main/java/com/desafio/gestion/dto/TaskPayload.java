package com.desafio.gestion.dto;

import com.desafio.gestion.validator.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskPayload {

    @NotNull
    @NotBlank(message = "Title no puede estar vacío")
    private String title;

    @NotNull
    @NotBlank(message = "Descripción no puede estar vacío")
    private String description;

    @NotNull
    @NotBlank(message = "dueDate no puede estar vacío")
    private LocalDate dueDate;

    @NotNull
    @NotBlank(message = "user no puede estar vacío")
    private Long userId;

    @ValueOfEnum(enumClass = TaskStatusEnum.class)
    private String status;
}
