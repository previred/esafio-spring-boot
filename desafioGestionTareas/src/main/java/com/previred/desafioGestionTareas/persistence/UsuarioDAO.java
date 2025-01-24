package com.previred.desafioGestionTareas.persistence;

import com.previred.desafioGestionTareas.entities.Usuario;

import java.util.Optional;

public interface UsuarioDAO {

    Optional<Usuario> findByUsername(String name);

    void save(Usuario user);
}