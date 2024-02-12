package com.previred.desafiobackend.domain.dto.task.request;

import com.previred.desafiobackend.domain.dto.enums.TaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTask {

    @NotNull(message = "Task description is required")
    private String description;

    @NotNull(message = "Task title is required")
    private String title;

    @NotNull(message = "Task's assigned user is required")
    private String asignedUserEmail;

}
