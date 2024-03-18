package com.nuevospa.taskmanager.domain.repository;

import com.nuevospa.taskmanager.domain.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    Usuarios findByUsername(String username);

    Usuarios findByUsernameAndPassword(String username, String password);
}
