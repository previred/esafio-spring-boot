package com.previred.challenge.dto;

import com.previred.challenge.enums.TaskStatus;

public record TaskResponseDTO(
        Integer id,
        String name,
        String description,
        TaskStatus status) {
}
