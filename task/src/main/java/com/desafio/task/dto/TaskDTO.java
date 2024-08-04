package com.desafio.task.dto;

import com.desafio.task.entity.StateTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private long id;

    private String name;

    private String description;

    private StateTask state;
}
