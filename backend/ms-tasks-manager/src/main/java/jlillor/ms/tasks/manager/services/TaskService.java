package jlillor.ms.tasks.manager.services;

import jlillor.ms.tasks.manager.dtos.NewTaskRequest;
import jlillor.ms.tasks.manager.dtos.TaskResponse;

import java.util.List;

/**
 * Interfaz de los servicios de tareas.
 *
 * @author Juan Lillo
 * @version 1.0
 * @see TaskResponse
 * @see NewTaskRequest
 * @since 1.0
 */
public interface TaskService {

    // -----------------------------------------------------------------------------------------
    // -- Métodos a implementar ----------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * Crea una nueva tarea.
     *
     * @param reqCreateTask {@link NewTaskRequest} datos de la tarea a crear
     * @param token         {@link String} token del usuario
     * @return {@link TaskResponse} tarea creada
     */
    TaskResponse create(NewTaskRequest reqCreateTask, String token);

    /**
     * Edita una tarea ya creada.
     *
     * @param reqEditTask {@link NewTaskRequest} datos de la tarea a editar
     * @param token       {@link String} token del usuario
     * @param idTask      {@link Long} id de la tarea a editar
     * @return {@link TaskResponse} tarea editada
     */
    TaskResponse edit(NewTaskRequest reqEditTask, String token, Long idTask);

    /**
     * Elimina una tarea.
     *
     * @param token  {@link String} token del usuario
     * @param idTask {@link Long} id de la tarea a eliminar
     */
    void delete(String token, Long idTask);

    /**
     * Obtiene todas las tareas de un usuario.
     *
     * @param token {@link String} token del usuario
     * @return {@link List<TaskResponse>} lista de tareas
     */
    List<TaskResponse> getAll(String token);

    /**
     * Obtiene una tarea por su id.
     *
     * @param idTask {@link Long} id de la tarea
     * @param token  {@link String} token del usuario
     * @return {@link TaskResponse} tarea encontrada
     */
    TaskResponse get(long idTask, String token);


}
