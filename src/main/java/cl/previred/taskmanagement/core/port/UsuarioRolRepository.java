package cl.previred.taskmanagement.core.port;

import cl.previred.taskmanagement.core.domain.entities.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {
    List<UsuarioRol> findByUsuarioRut(String rut);
}