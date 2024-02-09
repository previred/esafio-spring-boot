package com.previred.desafiobackend.domain.dto.task.request;

import com.previred.desafiobackend.domain.dto.enums.TaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

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

    private String description;
    private String title;
    private LocalDateTime creationDateTime;
    private LocalDateTime endDateTime;
    private String asignedUserDni;

}
