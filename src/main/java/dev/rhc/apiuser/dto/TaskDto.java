package dev.rhc.apiuser.dto;

import dev.rhc.apiuser.model.TaskState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;
    private TaskState taskState;
}
