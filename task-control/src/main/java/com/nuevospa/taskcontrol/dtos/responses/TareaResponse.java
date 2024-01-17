package com.nuevospa.taskcontrol.dtos.responses;

import com.nuevospa.taskcontrol.dtos.entities.HistorialEstado;
import com.nuevospa.taskcontrol.dtos.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TareaResponse {

    private long id;

    private String nombre;

    private String descripcion;

    private Usuario usuario;

    private List<HistorialEstado> historialEstados;
}
