package com.nuevospa.task.management.dto.task;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskDto {


	private String title;
	private String description;
	private LocalDateTime startDate;
	private LocalDateTime finishDate;
	private String status;
	private String priority;
	private String emailAsignado;
	
		
}
