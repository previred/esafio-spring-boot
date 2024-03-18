package cl.nuevospa.gestortareas.repository;

import cl.nuevospa.gestortareas.model.TareasEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasEstadoRepository extends JpaRepository<TareasEstado, Integer> {
}
