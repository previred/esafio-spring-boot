/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 19:46:43
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-11 19:53:59
 * @ Description:
 */

package spa.nuevo.desafiotecnico.service.implementations;

import lombok.AllArgsConstructor;
import spa.nuevo.desafiotecnico.model.Usuario;
import spa.nuevo.desafiotecnico.repository.UsuarioRepository;
import spa.nuevo.desafiotecnico.service.UsuarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

}
