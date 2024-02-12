package com.move.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.move.challenge.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
   Optional<UsuarioEntity> findByEmailAndClave(String email, String clave);

}
