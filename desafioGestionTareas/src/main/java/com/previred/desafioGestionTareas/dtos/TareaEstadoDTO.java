package com.previred.desafioGestionTareas.dtos;

import com.previred.desafioGestionTareas.entities.Tarea;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaEstadoDTO implements Serializable {

    private Long id;

    private String nameState;

    private List<Tarea> tasks;

}
