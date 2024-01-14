package jlillor.ms.tasks.manager.fixtures;

import jlillor.ms.tasks.manager.entities.Task;
import jlillor.ms.tasks.manager.entities.TaskStatus;
import jlillor.ms.tasks.manager.entities.User;

import java.time.LocalDateTime;

/**
 * Clase de utilidad para generar objetos para testeo.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
public class MsFixtures {

    /** Token de sesión. */
    public static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvMUBtYWlsLmNvbSIsImlhdCI6MTcwNTIxMjA5OSwianRpIjoiZmUwMmFkZTItYTllZi00NTVlLTllMDMtMzgwODVkMzY5OWIwIiwiZXhwIjoxNzA1MjEyMjc5fQ.P40DrUH9gEONmev7lmZEwSARbwHP6KqnFbGBGLtO1Pc";
    /** JSON de creación de tarea. */
    public static final String CREATE_REQUEST = "{\"title\": \"a totally normal title\", \"description\": \"totally normal description\" }";

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Genera un usuario para testeo.
     *
     * @return {@link User} usuario generado
     */
    public static User getUser() {
        final var user = new User();
        user.setEmail("user@mail.com");
        user.setId(10L);
        user.setCreated(LocalDateTime.now());
        user.setStatus(true);
        user.setName("super user");
        return user;
    }

    /**
     * Genera un estado de tarea para testeo.
     *
     * @return {@link TaskStatus} estado de tarea generado
     */
    public static TaskStatus getTaskStatus (){
        final var taskStatus = new TaskStatus();
        taskStatus.setId(1L);
        taskStatus.setStatus("TO DO");
        return taskStatus;
    }

    /**
     * Genera una tarea para testeo.
     *
     * @return {@link Task} tarea generada
     */
    public static Task getTask (){
        final var task = new Task();
        task.setId(1L);
        task.setTitle("a totally normal title");
        task.setDescription("totally normal description");
        task.setStartDate(LocalDateTime.now());
        task.setStatus(MsFixtures.getTaskStatus());
        task.setUser(MsFixtures.getUser());
        return task;
    }

}
