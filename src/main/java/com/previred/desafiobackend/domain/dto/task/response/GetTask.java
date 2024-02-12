package com.previred.desafiobackend.domain.dto.task.response;

import com.previred.desafiobackend.domain.dto.enums.TaskStatusEnum;
import com.previred.desafiobackend.domain.dto.user.response.GetUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTask {

    private TaskStatusEnum status;
    private String title;
    private String description;
    private LocalDateTime creationTimestamp;
    private LocalDateTime lastUpdateTimestamp;
    private Long identifier;
    private GetUser userInformation;

}
