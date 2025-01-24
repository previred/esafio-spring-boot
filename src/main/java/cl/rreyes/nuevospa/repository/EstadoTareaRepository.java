package cl.rreyes.nuevospa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.rreyes.nuevospa.model.EstadoTarea;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long>{

}
