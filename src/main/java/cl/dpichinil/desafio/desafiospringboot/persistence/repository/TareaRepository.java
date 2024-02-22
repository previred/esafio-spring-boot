package cl.dpichinil.desafio.desafiospringboot.persistence.repository;

import cl.dpichinil.desafio.desafiospringboot.persistence.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findAllByActivo(boolean activo);
}
