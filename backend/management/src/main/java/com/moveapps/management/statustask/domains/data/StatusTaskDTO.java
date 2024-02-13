package com.moveapps.management.statustask.domains.data;

import com.moveapps.management.task.domains.data.TaskDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusTaskDTO {
	private String id;
	private String description;
	private String status;
}
