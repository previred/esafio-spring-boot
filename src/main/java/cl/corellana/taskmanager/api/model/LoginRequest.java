package cl.corellana.taskmanager.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequest {

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("password")
    private String password = null;
}
