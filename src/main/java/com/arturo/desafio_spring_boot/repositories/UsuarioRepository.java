package com.arturo.desafio_spring_boot.repositories;

import org.springframework.stereotype.Repository;
import com.arturo.desafio_spring_boot.entities.UsuarioEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByNombreUsuario(String nombreUsuario);

}
