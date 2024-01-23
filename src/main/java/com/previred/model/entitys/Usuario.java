package com.previred.model.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long id;

    private String rut;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Tarea> tareas;

    @Override
    public String toString() {
        return String.format(
                "Usuario[id=%d, rut='%s', nombre='%s', apellido='%s', mail='%s']",
                id, rut, nombre, apellido, mail);
    }

}
