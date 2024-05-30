package com.nuevospa.taskmanager.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private UUID taskId;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String name;
    private String email;
    private String taskStatus;
    private UUID userId;
    private UUID taskStatusId;
}
