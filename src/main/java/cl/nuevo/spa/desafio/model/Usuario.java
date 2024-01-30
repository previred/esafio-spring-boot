package cl.nuevo.spa.desafio.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@Table(name = "CG_USER")
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String pass;
    private String email;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    private List<Tarea> tareas;

}
