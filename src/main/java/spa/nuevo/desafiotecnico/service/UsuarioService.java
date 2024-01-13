/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 19:29:31
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 21:53:18
 * @ Description:
 */

package spa.nuevo.desafiotecnico.service;

import spa.nuevo.desafiotecnico.dto.UsuarioDTO;
import spa.nuevo.desafiotecnico.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDTO> findAll();

    Optional<UsuarioDTO> findById(Integer id);

    UsuarioDTO save(Usuario usuario);

    void delete(Integer id);
}
