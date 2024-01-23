package com.previred.model.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "estados_tarea")
public class Estado_tarea implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idestadotarea")
    private Long id;
    private String estado;

    @JsonIgnore
    @OneToMany(mappedBy = "estadotarea")
    private List<Tarea> tareas;

    @Override
    public String toString() {
        return String.format(
                "Estado_tarea[id=%d, estado='%s']",
                id, estado);
    }
}
