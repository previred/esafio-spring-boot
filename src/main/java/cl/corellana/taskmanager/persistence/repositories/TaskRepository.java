package cl.corellana.taskmanager.persistence.repositories;

import cl.corellana.taskmanager.persistence.entities.TaskEntity;
import cl.corellana.taskmanager.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
