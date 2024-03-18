package cl.nuevospa.gestortareas.service.impl;

import cl.nuevospa.gestortareas.model.AuthPost200Response;
import cl.nuevospa.gestortareas.model.Usuarios;
import cl.nuevospa.gestortareas.repository.UsuariosRepository;
import cl.nuevospa.gestortareas.service.UsuariosService;
import cl.nuevospa.gestortareas.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServiceImpl implements UsuariosService {
    private final UsuariosRepository usuariosRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UsuariosServiceImpl(UsuariosRepository usuariosRepository, JwtUtil jwtUtil) {
        this.usuariosRepository = usuariosRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<Usuarios> findAllUsuarios() {
        return usuariosRepository.findAll();
    }

    @Override
    public Optional<Usuarios> findUsuarioById(Integer id) {
        return usuariosRepository.findById(id);
    }

    @Override
    public Void addUsuario(Usuarios usuario) {
        usuario.setFechaCreacion(OffsetDateTime.now());
        usuario.setFechaActualizacion(OffsetDateTime.now());
        usuariosRepository.save(usuario);
        return null;
    }

    @Override
    public Void updateUsuario(Integer id, Usuarios usuario) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuarios usuarioActual = optionalUsuario.get();
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setApellido(usuario.getApellido());
            usuarioActual.setCorreo(usuario.getCorreo());
            usuarioActual.setPassword(usuario.getPassword());
            usuarioActual.setFechaActualizacion(OffsetDateTime.now());
            usuariosRepository.save(usuarioActual);
        } else {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        return null;
    }

    @Override
    public Void deleteUsuario(Integer id) {
        usuariosRepository.deleteById(id);
        return null;
    }

    @Override
    public AuthPost200Response login(UserDetails userDetails) {
        AuthPost200Response authPost200Response = new AuthPost200Response();
        authPost200Response.token(jwtUtil.generateToken(userDetails));

        return authPost200Response;
    }
}
