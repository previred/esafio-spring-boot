package cl.tecnova.pruebatecnica.model;

import cl.tecnova.pruebatecnica.dto.TareaDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TAREAS")
@Data
public class TareasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAREAS_GENERATOR")
    @SequenceGenerator(name = "TAREAS_GENERATOR", sequenceName = "TAREAS_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ESTADO_TAREA_ID")
    private EstadosTareaEntity estado;

    public TareaDTO toDTO() {
        String nombreEstado = estado == null ? null : estado.getEstado();
        TareaDTO dto = new TareaDTO();
        dto.setId(id);
        dto.setNombre(nombre);
        dto.setEstado(nombreEstado);

        return dto;
    }

}
