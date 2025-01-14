package com.previred.gestion_tareas_api.dtos;

import java.io.Serializable;
import java.util.List;

import com.previred.gestion_tareas_api.entities.Task;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskStateDTO implements Serializable{

    
    private Long id;
    
    private String nameState;


    private List<Task> tasks;
 
   

}

