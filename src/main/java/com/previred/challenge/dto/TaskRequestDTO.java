package com.previred.challenge.dto;

import com.previred.challenge.enums.TaskStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record TaskRequestDTO(
        @NotEmpty String name,
        String description,
        @NotNull TaskStatus status) {
}
