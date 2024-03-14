package com.desafio.gestion.dto;

import com.desafio.gestion.validator.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskPayload {

    private String title;
    private String description;
    private LocalDate dueDate;
    private Long userId;
    @ValueOfEnum(enumClass = TaskStatusEnum.class)
    private String status;
}
