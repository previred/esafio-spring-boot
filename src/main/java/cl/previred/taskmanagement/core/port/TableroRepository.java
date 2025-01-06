package cl.previred.taskmanagement.core.port;

import cl.previred.taskmanagement.core.domain.entities.Tablero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TableroRepository extends JpaRepository<Tablero, String> {

    List<Tablero> findAll();
    Optional<Tablero> findByCodigo(String codigo);
    Tablero save(Tablero tablero);

}
