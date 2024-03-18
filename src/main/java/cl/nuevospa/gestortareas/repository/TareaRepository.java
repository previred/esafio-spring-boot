package cl.nuevospa.gestortareas.repository;

import cl.nuevospa.gestortareas.model.Tareas;
import cl.nuevospa.gestortareas.model.TareasEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tareas, Integer> {
    List<Tareas> findAllByEstado(TareasEstado estado);
}
