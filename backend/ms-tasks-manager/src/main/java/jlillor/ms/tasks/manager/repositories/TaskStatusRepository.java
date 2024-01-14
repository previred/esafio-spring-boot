package jlillor.ms.tasks.manager.repositories;

import jlillor.ms.tasks.manager.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de estados de tarea.
 *
 * @see JpaRepository
 * @see TaskStatus
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

    // --------------------------------------------------------------------------------------
    // -- Métodos Customs -------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    /**
     * Busca un estado de tarea por su status.
     *
     * @param status {@link String} status del estado de tarea
     * @return estado de tarea {@link TaskStatus} estado de tarea encontrado
     */
    Optional<TaskStatus> findByStatus(String status);

}
