package co.com.task.api.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {

    private UUID idTask;
    @NotEmpty(message = "El campo description no puede estar vacio")
    private String description;
    @NotEmpty(message = "El status description no puede estar vacio")
    private String status;
    private String created;
    private String modified;
    private String name;
    private String email;
    private UUID idUser;
}
