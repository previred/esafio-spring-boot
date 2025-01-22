package com.previred.desafio_spring_boot.repository;

import com.previred.desafio_spring_boot.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
