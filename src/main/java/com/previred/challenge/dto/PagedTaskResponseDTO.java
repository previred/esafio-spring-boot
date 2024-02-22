package com.previred.challenge.dto;

import java.util.List;

public record PagedTaskResponseDTO(
        List<TaskResponseDTO> taskList,
        Integer totalPages,
        Long total
) {
}
