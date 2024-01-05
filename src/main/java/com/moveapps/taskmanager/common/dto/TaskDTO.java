package com.moveapps.taskmanager.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

public record TaskDTO(
    String name,
    String externalId,
    @JsonIgnore
    Long id,
    LocalDateTime createdAt,
    LocalDateTime modifiedAt
) { }
