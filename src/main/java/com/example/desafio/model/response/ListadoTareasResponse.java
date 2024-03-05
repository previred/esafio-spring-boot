package com.example.desafio.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListadoTareasResponse implements Serializable {
    private Integer id;
    private String nombre;
    private String nombre_usuario;
    private String estado_tarea;

}
