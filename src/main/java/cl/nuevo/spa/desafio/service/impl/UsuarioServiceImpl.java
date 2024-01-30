package cl.nuevo.spa.desafio.service.impl;

import cl.nuevo.spa.desafio.dto.UsuarioDTO;
import cl.nuevo.spa.desafio.model.Usuario;
import cl.nuevo.spa.desafio.repository.UsuarioRepository;
import cl.nuevo.spa.desafio.service.UsuarioService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCrypt;
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
                .toList();
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
        usuario.setPass(BCrypt.hashpw(usuario.getPass(), BCrypt.gensalt()));
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
