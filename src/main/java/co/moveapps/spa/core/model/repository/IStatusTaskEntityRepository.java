package co.moveapps.spa.core.model.repository;

import co.moveapps.spa.core.model.entity.StatusTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusTaskEntityRepository extends JpaRepository<StatusTaskEntity, Integer> {

}
