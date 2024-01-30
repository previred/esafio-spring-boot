package cl.nuevo.spa.desafio.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CG_TASK_STATUS")
public class EstadoTarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String estado;
}
