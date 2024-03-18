package cl.nuevospa.gestortareas.repository;

import cl.nuevospa.gestortareas.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    Usuarios findByUsername(String username);
}
