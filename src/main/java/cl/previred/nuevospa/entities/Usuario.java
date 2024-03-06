package cl.previred.nuevospa.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, name = "contrasena")
    private String password;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

}