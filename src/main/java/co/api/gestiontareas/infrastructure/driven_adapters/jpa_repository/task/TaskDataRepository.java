package co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.task;

import co.api.gestiontareas.infrastructure.driven_adapters.jpa_repository.task.entities.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TaskDataRepository extends CrudRepository<TaskEntity, Long>, QueryByExampleExecutor<TaskEntity> {

}
