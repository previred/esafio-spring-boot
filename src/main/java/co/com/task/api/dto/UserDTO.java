package co.com.task.api.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private UUID idUser;
    @NotEmpty(message = "El email no puede estar vacio")
    private String email;
    @NotEmpty(message = "El name no puede estar vacio")
    private String name;
    @NotEmpty(message = "El password no puede estar vacio")
    private String password;

}
