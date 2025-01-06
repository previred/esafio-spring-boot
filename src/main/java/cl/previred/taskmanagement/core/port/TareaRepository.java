package cl.previred.taskmanagement.core.port;

import cl.previred.taskmanagement.core.domain.entities.Tarea;
import cl.previred.taskmanagement.core.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea, String> {
    Tarea save(Tarea task);
    Optional<Tarea> findByCodigo(Long codigo);
    List<Tarea> findAll();
    List<Tarea> findByUsuario(Usuario usuario);
    void deleteByCodigo(Long codigo);
}
