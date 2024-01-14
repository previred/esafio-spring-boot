package jlillor.ms.tasks.manager.services;

import jlillor.ms.tasks.manager.entities.TaskStatus;

/**
 * Servicio para la gestión de estados de tareas.
 *
 * @author Juan Lillo
 * @version 1.0
 * @see TaskStatus
 * @since 1.0
 */
public interface TaskStatusService {

    // -----------------------------------------------------------------------------------------
    // -- Métodos a implementar ----------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * Obtiene un estado de tarea por su nombre.
     *
     * @param status {@link String} nombre del estado
     * @return {@link TaskStatus} estado encontrado
     */
    TaskStatus getStatus(String status);

}
