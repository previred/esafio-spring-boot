package com.moveapps.management.task.infraestructure.adapters;

import com.moveapps.management.task.domains.data.TaskDTO;
import com.moveapps.management.task.infraestructure.entities.TaskEntity;

public interface ITaskAdapter  {
	TaskEntity toEntity(TaskDTO taskDTO);
}
