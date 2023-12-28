package com.example.gestiontareas.domain.repository;

import com.example.gestiontareas.domain.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
