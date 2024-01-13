/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 19:46:43
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 21:53:57
 * @ Description:
 */

package spa.nuevo.desafiotecnico.service.implementations;

import lombok.AllArgsConstructor;
import spa.nuevo.desafiotecnico.dto.UsuarioDTO;
import spa.nuevo.desafiotecnico.model.Usuario;
import spa.nuevo.desafiotecnico.repository.UsuarioRepository;
import spa.nuevo.desafiotecnico.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getUsername(),
                        usuario.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDTO> findById(Integer id) {
        return usuarioRepository.findById(id)
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getUsername(),
                        usuario.getEmail()));
    }

    @Override
    public UsuarioDTO save(Usuario usuario) {
        usuario.setPass(passwordEncoder.encode(usuario.getPass()));
        if (usuario.getId() == null) {
            Optional<Integer> maxID = usuarioRepository.findAll()
                    .stream().map(Usuario::getId)
                    .max(Integer::compare);
            usuario.setId(maxID.orElse(0) + 1);
        }
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail());
    }

    @Override
    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }

}
