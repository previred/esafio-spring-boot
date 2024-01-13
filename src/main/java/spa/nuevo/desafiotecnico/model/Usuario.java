/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 19:03:40
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 21:02:05
 * @ Description:
 */

package spa.nuevo.desafiotecnico.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@Table(name = "usuarios")
@ToString
public class Usuario {

    @Id
    /**
     * Se comenta la autogeneracion de ID por un fallo en H2, resolviendo entregar
     * el ID en el servicio
     */
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String pass;
    private String email;

    @JsonBackReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tarea> tareas;

}
