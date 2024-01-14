package jlillor.ms.tasks.manager.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Entidad que representa a una tarea.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Entity
@Data
@Table(name = "tareas")
public class Task {

    /** Identificador de la tarea. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** El título. */
    @Column(name = "title", nullable = false)
    private String title;
    /** La descripción. */
    @Column(name = "description")
    private String description;
    /** La fecha de inicio. */
    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;
    /** La fecha de finalización. */
    @Column(name = "endDate")
    private LocalDateTime endDate;
    /** El usuario de la tarea. */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    /** El estado de la tarea. */
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private TaskStatus status;

}
