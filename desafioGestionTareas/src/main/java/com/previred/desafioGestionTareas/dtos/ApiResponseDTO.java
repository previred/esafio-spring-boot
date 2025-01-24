package com.previred.desafioGestionTareas.dtos;

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

    public ApiResponseDTO(boolean b, String s, int status) {
        this.success = b;
        this.message = s;
        this.data   = status;
    }

    public ApiResponseDTO(boolean b, String s, String ex) {
        this.success = b;
        this.message = s;
        this.data   = ex;
    }
}
