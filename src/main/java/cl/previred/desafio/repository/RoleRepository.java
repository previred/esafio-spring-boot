package cl.previred.desafio.repository;

import cl.previred.desafio.entity.RoleEntity;
import cl.previred.desafio.enums.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
