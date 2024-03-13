package cl.gestiontareasprevired.repository;

import cl.gestiontareasprevired.model.EstadosTarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadosTareaRepository extends JpaRepository<EstadosTarea, String> {
}