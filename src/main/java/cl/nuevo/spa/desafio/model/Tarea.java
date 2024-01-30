package cl.nuevo.spa.desafio.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Table(name = "CG_TASK")
@ToString
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "estado_tarea_id")
    private EstadoTarea estadoTarea;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
