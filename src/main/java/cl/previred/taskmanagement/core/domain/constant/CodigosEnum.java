package cl.previred.taskmanagement.core.domain.constant;

public enum CodigosEnum {

    EXITO("00000", "Operación exitosa"),
    TOKEN_VALIDO("00002", "Token Válido"),
    USUARIO_NO_ENCONTRADO("00003", "Usuario no encontrado. No se puede crear la tarea"),
    NO_SE_ENCUENTRAN_TAREAS("00004", "No se encuentran tareas"),
    NO_SE_ENCUENTRA_TABLERO("00005", "No se encuentra el tablero"),
    TABLERO_DUPLICADO("00006", "El codigo del tablero ya existe"),
    REQUEST_INVALIDO("00007", "El request no es válido"),
    CREDENCIALES_INCORRECTAS("00008", "Credenciales incorrectas"),
    TOKEN_INVALIDO("00008", "Token inválido"),
    ERROR_DEL_SERVIDOR("00009", "Error interno del servidor");



    private final String code;
    private final String message;

    CodigosEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
