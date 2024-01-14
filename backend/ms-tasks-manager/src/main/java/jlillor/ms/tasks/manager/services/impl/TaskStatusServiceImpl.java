package jlillor.ms.tasks.manager.services.impl;

import jlillor.ms.tasks.manager.entities.TaskStatus;
import jlillor.ms.tasks.manager.exceptions.MsNotFoundException;
import jlillor.ms.tasks.manager.properties.MessageProperty;
import jlillor.ms.tasks.manager.repositories.TaskStatusRepository;
import jlillor.ms.tasks.manager.services.TaskStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementación de la interfaz de servicio de estados de tareas.
 *
 * @see TaskStatusService
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Service
@RequiredArgsConstructor
public class TaskStatusServiceImpl implements TaskStatusService {

    /** Repositorio de estados de tareas. */
    private final TaskStatusRepository taskStatusRepository;
    /** Propiedades de mensajes. */
    private final MessageProperty msgProperty;

    // -----------------------------------------------------------------------------------------
    // -- Métodos sobreescritos ----------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public TaskStatus getStatus(final String status) {
        final var statusObject = taskStatusRepository.findByStatus(status);
        if (statusObject.isEmpty()) {
            throw new MsNotFoundException(msgProperty.getGenericNotFound());
        }
        return statusObject.get();
    }

}
