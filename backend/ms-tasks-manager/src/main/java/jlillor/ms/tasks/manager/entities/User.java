package jlillor.ms.tasks.manager.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa a un usuario.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Entity
@Data
@Table(name = "usuarios")
public class User {

    /** Identificador del usuario. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** Nombre del usuario. */
    @Column(name = "name", length = 50)
    private String name;
    /** email del usuario. */
    @Column(name = "email", nullable = false, length = 256, unique = true)
    private String email;
    /** Contraseña del usuario. */
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    /** Estado del usuario. */
    @Column(name = "status", nullable = false)
    private boolean status;
    /** Fecha de creación del usuario. */
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    /** Fecha de la última sesión. */
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    /** Lista de tareas del usuario. */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> task;

}
