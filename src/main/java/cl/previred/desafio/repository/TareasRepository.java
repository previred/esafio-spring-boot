package cl.previred.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.previred.desafio.model.Tareas;

@Repository
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
}
