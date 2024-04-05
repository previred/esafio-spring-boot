package co.moveapps.spa.core.model.repository;

import co.moveapps.spa.core.model.entity.TaskEntity;
import co.moveapps.spa.core.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITaskEntityRepository extends JpaRepository<TaskEntity, UUID> {

}
