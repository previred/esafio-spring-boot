package cl.previred.desafio.repository;

import cl.previred.desafio.entity.StateTaskEntity;
import cl.previred.desafio.enums.StateTaskEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateTaskRepository extends CrudRepository<StateTaskEntity, Long> {
    Optional<StateTaskEntity> findByName(StateTaskEnum name);
}
