package com.nuevospa.taskcontrol.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddTareaRequest {

    @NotBlank
    private String nombre;

    private String descripcion;

    @NotBlank
    private long idUsuario;
}
