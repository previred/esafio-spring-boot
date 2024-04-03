package com.previred.desafioApiRest.desafioprevired.service;

import com.previred.desafioApiRest.desafioprevired.repository.model.Tarea;
import com.previred.desafioApiRest.desafioprevired.repository.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario create(Usuario usuario);

    Usuario findById(Long id);

    List<Usuario> obtenerUsuarios();

    void eliminarTarea(Long id);
}
