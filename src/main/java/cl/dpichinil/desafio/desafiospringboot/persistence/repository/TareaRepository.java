package cl.dpichinil.desafio.desafiospringboot.persistence.repository;

import cl.dpichinil.desafio.desafiospringboot.persistence.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
}
