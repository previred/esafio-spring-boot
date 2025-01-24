package cl.gestion.tarea.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthResponse implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
