package cl.dpichinil.desafio.desafiospringboot.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="estado_tarea", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoTarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "descripcion", length = 30)
    private String descripcion;

    @Column(name = "activo")
    private String activo;
}
