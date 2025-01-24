package cl.rreyes.nuevospa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.rreyes.nuevospa.model.Tarea;

@Repository
public interface TareaRepository  extends JpaRepository<Tarea, Long>{

}
