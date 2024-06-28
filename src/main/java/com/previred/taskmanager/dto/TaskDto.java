package com.previred.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 64, message = "Title must be less than or equals to 64 characters")
    private String title;

    @Size(max = 255, message = "Description must be less than or equal to 255 characters")
    private String description;

    @NotBlank(message = "Status is mandatory")
    private String status;

    @NotBlank(message = "Priority is mandatory")
    private String priority;

    @NotNull(message = "Start date is mandatory")
    private LocalDateTime startDate;

    @NotNull(message = "Due date is mandatory")
    @FutureOrPresent(message = "Due date must be in the present or future")
    private LocalDateTime dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull(message = "User ID is mandatory")
    private Long userId;
}
