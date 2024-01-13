/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-12 21:18:04
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 21:31:06
 * @ Description:
 */

package spa.nuevo.desafiotecnico.dto;

import java.util.Date;

public record TareaDTO(Long id, String nombre, String descripcion, String estado, Date fecha, UsuarioDTO usuario) {

}
