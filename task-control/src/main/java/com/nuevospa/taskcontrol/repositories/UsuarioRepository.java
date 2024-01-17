package com.nuevospa.taskcontrol.repositories;

import com.nuevospa.taskcontrol.dtos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    Optional<Usuario> findByNombreAndClave(String nombre, String clave);
}
