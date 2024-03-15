package cl.nuevospa.application.port.out;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.nuevospa.domain.h2.entities.StatusDTO;

public interface StatusRepository extends CrudRepository<StatusDTO, Integer>{
    Optional<StatusDTO> findByCode(Integer code);
}
