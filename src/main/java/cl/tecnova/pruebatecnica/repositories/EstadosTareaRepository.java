package cl.tecnova.pruebatecnica.repositories;

import cl.tecnova.pruebatecnica.model.EstadosTareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosTareaRepository extends JpaRepository<EstadosTareaEntity, Integer> {

}
