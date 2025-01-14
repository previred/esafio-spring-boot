package com.previred.gestion_tareas_api.dtos;

import com.previred.gestion_tareas_api.entities.TaskState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO{

    private Long id;

    private String description;

    private TaskState state;


}
