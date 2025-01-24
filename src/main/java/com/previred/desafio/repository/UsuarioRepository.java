package com.previred.desafio.repository;

import com.previred.desafio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UsuarioRepository.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
