package jlillor.ms.tasks.manager.services.impl;

import jlillor.ms.tasks.manager.dtos.NewTaskRequest;
import jlillor.ms.tasks.manager.dtos.TaskResponse;
import jlillor.ms.tasks.manager.entities.Task;
import jlillor.ms.tasks.manager.exceptions.MsInternalError;
import jlillor.ms.tasks.manager.exceptions.MsNotFoundException;
import jlillor.ms.tasks.manager.mappers.TaskMapper;
import jlillor.ms.tasks.manager.properties.MessageProperty;
import jlillor.ms.tasks.manager.repositories.TaskRepository;
import jlillor.ms.tasks.manager.services.AuthentificationService;
import jlillor.ms.tasks.manager.services.TaskService;
import jlillor.ms.tasks.manager.services.TaskStatusService;
import jlillor.ms.tasks.manager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz de servicio de tareas.
 *
 * @see TaskService
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    /** Repositorio de tareas. */
    private final TaskRepository repositoryTask;
    /** Servicio de autentificación. */
    private final AuthentificationService authService;
    /** Servicio de usuarios. */
    private final UserService userService;
    /** Servicio de estados de tareas. */
    private final TaskStatusService taskStatusService;
    /** Propiedades de mensajes. */
    private final MessageProperty msgProperty;
    /** Mapper de tareas. */
    private final TaskMapper mapper = Mappers.getMapper(TaskMapper.class);

    // -----------------------------------------------------------------------------------------
    // -- Métodos sobreescritos ----------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponse create(final NewTaskRequest requestBody, final String token) {
        authService.validate(token);
        final var user = userService.getUserByToken(token);
        final var status = taskStatusService.getStatus("TO DO");
        try {
            final var saveTask = repositoryTask.save(mapper.toEntity(requestBody, user, status));
            return mapper.fromEntity(saveTask);
        } catch (Exception e) {
            throw new MsInternalError(msgProperty.getGenericError());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponse edit(final NewTaskRequest requestBody, final String token, final Long idTask) {
        authService.validate(token);
        final var task = this.get(idTask);
        final var status = taskStatusService.getStatus(requestBody.getStatus());
        final var user = userService.getUserByToken(token);
        try {
            task.setTitle(requestBody.getTitle());
            task.setDescription(requestBody.getDescription());
            task.setStatus(status);
            task.setUser(user);
            task.setEndDate(status.getStatus().equals("DONE") ? LocalDateTime.now() : null);
            Task saveTask = repositoryTask.save(task);
            return mapper.fromEntity(saveTask);
        } catch (Exception e) {
            throw new MsInternalError(msgProperty.getGenericError());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final String token, final Long idTask) {
        authService.validate(token);
        final var taskDao = repositoryTask.findById(idTask);
        if (taskDao.isEmpty()) {
            throw new MsNotFoundException(msgProperty.getGenericNotFound());
        }
        try {
            repositoryTask.deleteById(idTask);
        } catch (Exception e) {
            throw new MsInternalError(msgProperty.getGenericError());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskResponse> getAll(final String token) {
        authService.validate(token);
        final var taskListDao = repositoryTask.findAll();
        if (taskListDao.isEmpty()) {
            throw new MsNotFoundException(msgProperty.getGenericNotFound());
        }
        return taskListDao.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponse get(final long idTask, final String token) {
        authService.validate(token);
        return mapper.fromEntity(this.get(idTask));
    }

    // -----------------------------------------------------------------------------------------
    // -- Métodos privados ---------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * Obtiene una tarea por su id.
     *
     * @param idTask {@link Long} id de la tarea
     * @return {@link Task} tarea encontrada
     */
    private Task get(final long idTask) {
        final var task = repositoryTask.findById(idTask);
        if (task.isEmpty()) {
            throw new MsNotFoundException(msgProperty.getGenericNotFound());
        }
        return task.get();
    }

}
