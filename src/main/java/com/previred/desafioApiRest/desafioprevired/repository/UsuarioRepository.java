package com.previred.desafioApiRest.desafioprevired.repository;

import com.previred.desafioApiRest.desafioprevired.repository.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);
}
