package com.desafio.desafiospringboot.model.repositories;

import com.desafio.desafiospringboot.model.dao.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorioJPA extends JpaRepository<Usuario,Long> {
    @Query(value = "SELECT u FROM Usuario u WHERE u.email=?1 AND u.password=?2")
    Optional<Usuario> buscarUsuario(String email, String password);

}
