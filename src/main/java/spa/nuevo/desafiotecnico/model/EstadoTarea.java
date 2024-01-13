/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 19:05:39
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 20:35:02
 * @ Description:
 */

package spa.nuevo.desafiotecnico.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "estados_tarea")
public class EstadoTarea {
    @Id
    /**
     * Se comenta la autogeneracion de ID por un fallo en H2, resolviendo entregar
     * el ID en el servicio
     */
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String estado;

}
