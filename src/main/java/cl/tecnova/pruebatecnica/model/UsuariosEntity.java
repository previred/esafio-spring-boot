package cl.tecnova.pruebatecnica.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USUARIOS")
@Data
public class UsuariosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_GENERATOR")
    @SequenceGenerator(name = "USUARIOS_GENERATOR", sequenceName = "USUARIOS_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

}
