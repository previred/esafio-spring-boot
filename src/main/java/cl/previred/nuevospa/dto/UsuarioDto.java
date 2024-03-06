package cl.previred.nuevospa.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Integer id;
    @NotBlank
    private String username;
    @NotBlank
    private String nombre;
    @NotNull
    @Email
    private String email;
    @NotNull
    private RolDto rol;
}
