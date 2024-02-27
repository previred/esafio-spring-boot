package cl.tecnova.pruebatecnica.repositories;

import cl.tecnova.pruebatecnica.model.TareasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasRepository extends JpaRepository<TareasEntity, Integer> {

}
