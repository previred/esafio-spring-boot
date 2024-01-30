package cl.nuevo.spa.desafio.repository;

import cl.nuevo.spa.desafio.model.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Integer> {

}
