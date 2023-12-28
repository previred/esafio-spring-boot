package com.nuevospa.task.management.dto.task;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTaskDto {
	
	private Long idTask;
	private String title;
	private String description;
	private LocalDateTime startDate;
	private LocalDateTime finishDate;
	private String status;
	@Enumerated(EnumType.STRING)
	private Priority priority;
	private String assigned;

}
