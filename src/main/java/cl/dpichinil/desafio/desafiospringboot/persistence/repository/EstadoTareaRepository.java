package cl.dpichinil.desafio.desafiospringboot.persistence.repository;

import cl.dpichinil.desafio.desafiospringboot.persistence.entity.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Integer> {

}
