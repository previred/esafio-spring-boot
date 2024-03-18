package cl.nuevospa.gestortareas.service;


import cl.nuevospa.gestortareas.model.Usuarios;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuarios> findUsuario(Integer id);
}
