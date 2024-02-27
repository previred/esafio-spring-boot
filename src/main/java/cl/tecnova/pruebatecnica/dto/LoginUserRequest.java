package cl.tecnova.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginUserRequest {

    @Schema(name = "username", example = "Test1")
    private String username;

    @Schema(name = "password", example = "test1")
    private String password;

}
