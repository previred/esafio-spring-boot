package repository;

import model.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long> {
}