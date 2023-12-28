package org.tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tareas.model.Usuario;

import java.util.List;

public interface UsersRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAll();

}