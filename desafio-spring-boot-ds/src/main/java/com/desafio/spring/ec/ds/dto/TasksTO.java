package com.desafio.spring.ec.ds.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TasksTO {

    private String idTasks;
    private String description;
    private StatusTO status;
}
