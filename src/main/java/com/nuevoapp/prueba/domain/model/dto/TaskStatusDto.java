package com.nuevoapp.prueba.domain.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskStatusDto {
    private String status;
    private String description;
}