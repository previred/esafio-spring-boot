package cl.nuevospa.gestortareas.service;


import cl.nuevospa.gestortareas.model.AuthPost200Response;
import cl.nuevospa.gestortareas.model.Usuarios;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuariosService {
    List<Usuarios> findAllUsuarios();

    Optional<Usuarios> findUsuarioById(Integer id);

    Void addUsuario(Usuarios usuarios);

    Void updateUsuario(Integer id, Usuarios usuarios);

    Void deleteUsuario(Integer id);

    AuthPost200Response login(UserDetails userDetails);
}
