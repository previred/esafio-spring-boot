package com.previred.dto;

import com.previred.model.entitys.Tarea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response{
    private int estado;
    private String codigo;
    private String mensaje;
    private Tarea tarea;
}
