package com.nuevospa.app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequestDto {
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be at most 255 characters long")
    private String title;
    @Size(max = 1000, message = "Detail must be at most 1000 characters long")
    private String detail;
    @Pattern(regexp = "^(Pending|In progress|Completed)$", message = "Invalid task status identifier ('Pending','In progress','Completed')")
    private String status;
}