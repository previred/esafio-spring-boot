package cl.nuevospa.gestortareas.service.impl;

import cl.nuevospa.gestortareas.model.Usuarios;
import cl.nuevospa.gestortareas.repository.UsuarioRepository;
import cl.nuevospa.gestortareas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuarios> findUsuario(Integer id) {
        return usuarioRepository.findById(id);
    }
}
