package cl.previred.taskmanagement.core.port;

import cl.previred.taskmanagement.core.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByRut(String rut);
    Optional<Usuario> findByUsuario(String usuario);

}
