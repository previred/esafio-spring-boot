package cl.corellana.taskmanager.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignUpRequest {
    @JsonProperty("email")
    private String email = null;

    @JsonProperty("fullname")
    private String fullname = null;

    @JsonProperty("password")
    private String password = null;


}
