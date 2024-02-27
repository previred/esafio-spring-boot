package cl.tecnova.pruebatecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {

    private String username;
    private String token;

}
