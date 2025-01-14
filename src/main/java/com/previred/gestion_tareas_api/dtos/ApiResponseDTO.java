package com.previred.gestion_tareas_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseDTO{

    private boolean success;

    private String message;
    
    private Object data;

   
}
