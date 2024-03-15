package cl.nuevospa.config.exceptions.errors;

public enum ErrorCode {
    TASK_RESOURCE_ACCESS_ERROR(2004, "No se encontro la tarea"),
    
    
    STATUS_RESOURCE_ACCESS_ERROR(3004, "Estado invalido"),
    USER_NOT_FOUND(4004,"Usuario o clave incorrectos");


    private Integer code;
    private String message;
    ErrorCode(Integer code, String message) {
     this.code=code;
     this.message=message;
    }
    public Integer code() {
        return this.code;
    }
    public String message() {
        return this.message;
    }
}
