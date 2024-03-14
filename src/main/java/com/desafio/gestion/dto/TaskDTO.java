package com.desafio.gestion.dto;

import com.desafio.gestion.validator.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private UserPayload user;
    @ValueOfEnum(enumClass = TaskStatusEnum.class)
    private String status;
}
