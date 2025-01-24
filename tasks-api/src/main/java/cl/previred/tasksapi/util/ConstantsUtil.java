package cl.previred.tasksapi.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantsUtil {
    public static final String ERROR_MESSAGE_USER_NOT_FOUND = "Usuario o contraseña incorrecta";
    public static final String ERROR_MESSAGE_NO_DATA_FOUND = "No se encuentra el registro en la base de datos";
    public static final String ERROR_MESSAGE_NO_TASK_STATUS_FOUND = "No se encuentra el registro del estado de la tarea ingresado (%d), intente otro task_status_id";
    public static final String ERROR_MESSAGE_NO_TASK_ID_FOUND = "No se encuentra tarea consultada (%d)";
    public static final String ERROR_MESSAGE_NO_TASK_NAME_FOUND = "No se encuentra tarea consultada (%s";
}
