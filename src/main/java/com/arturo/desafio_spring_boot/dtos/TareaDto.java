package com.arturo.desafio_spring_boot.dtos;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaDto {

    private Optional<Long> id;
    private String nombre;
    private String descripcion;
    private Long estado_tarea_id;

}
