package com.nuevoapp.prueba.domain.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class TasksDto {
    private Integer id;
    private String description;
    private UserDto user;
    private TaskStatusDto status;
    private String comment;
    private ZonedDateTime assignedDate;
    private ZonedDateTime initDate;
    private ZonedDateTime blockedDate;
    private ZonedDateTime completedDate;
    private ZonedDateTime lastModifiedDate;
}
