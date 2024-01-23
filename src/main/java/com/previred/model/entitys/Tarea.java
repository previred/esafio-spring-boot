package com.previred.model.entitys;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "tareas")
public class Tarea implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idtarea")
    private Long id;

    @Min(value = 3, message = "Nombre acepta mínimo 3 caracteres")
    @Max(value = 45, message = "Nombre acepta máximo 45 caracteres")
    private String nombre;

    @Min(value = 3, message = "Descripción acepta mínimo 3 caracteres")
    @Max(value = 45, message = "Descripción acepta máximo 45 caracteres")
    private String descripcion;

    @Min(value = 3, message = "Prioridad acepta mínimo 3 caracteres")
    @Max(value = 45, message = "Prioridad acepta máximo 45 caracteres")
    private String prioridad;

    @NotNull(message = "La fecha de inicio no puede ser nulo")
    private Date fecha_inicio;
    @NotNull(message = "La fecha de término no puede ser nulo")
    private Date fecha_termino;


    @ManyToOne
    @JoinColumn(name = "idusuario",nullable = false)
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "idestadotarea",nullable = false)
    private Estado_tarea estadotarea;

    @Override
    public String toString() {
        return String.format(
                "Tarea[id=%d, nombre='%s', descripcion='%s', prioridad='%s']",
                id, nombre, descripcion,prioridad);
    }
}
