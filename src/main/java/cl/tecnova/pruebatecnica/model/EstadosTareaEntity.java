package cl.tecnova.pruebatecnica.model;

import cl.tecnova.pruebatecnica.dto.EstadoDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Table(name = "ESTADOS_TAREA")
@Data
public class EstadosTareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADOS_TAREA_GENERATOR")
    @SequenceGenerator(name = "ESTADOS_TAREA_GENERATOR", sequenceName = "ESTADOS_TAREA_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ESTADO")
    private String estado;

    public EstadoDTO toDTO() {
        EstadoDTO dto = new EstadoDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
