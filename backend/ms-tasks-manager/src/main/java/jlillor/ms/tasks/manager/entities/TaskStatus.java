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
import java.util.List;

/**
 * Entidad que representa a un estado de una tarea.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Entity
@Data
@Table(name = "estados_tarea")
public class TaskStatus {

    /** Identificador del estado. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** Nombre del estado. */
    @Column(name = "status", nullable = false)
    private String status;
    /** Lista de tareas asociadas al estado. */
    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> task;

}
