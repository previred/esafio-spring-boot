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
@Table(name = "estados_tarea")
public class EstadoTarea {

    public static enum ESTADOS {
        PENDIENTE(1), EN_CURSO(2), TERMINADA(3);
        
        private final Integer idEstado;

        ESTADOS(Integer idEstado){
            this.idEstado = idEstado;
        }

        public Integer getValue() {
            return idEstado;
        }
    
        public static boolean isValid(Integer value) {
            return PENDIENTE.getValue().equals(value)
                || EN_CURSO.getValue().equals(value)
                || TERMINADA.getValue().equals(value);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombre;
}
