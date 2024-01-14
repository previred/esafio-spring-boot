package jlillor.ms.tasks.manager.repositories;

import jlillor.ms.tasks.manager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de tareas.
 *
 * @see JpaRepository
 * @see Task
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
