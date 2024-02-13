package com.moveapps.management.task.infraestructure.adapters;

import static com.moveapps.management.user.infraestructure.confing.Constants.MANAGEMENT_MODULE;
import static com.moveapps.management.user.infraestructure.confing.Constants.TASK_ADAPTER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moveapps.commons.api.infraestructure.adapters.EndPointGenericAdapterImpl;
import com.moveapps.management.statustask.infraestructure.adapters.StatusTaskAdapter;
import com.moveapps.management.task.domains.data.TaskDTO;
import com.moveapps.management.task.infraestructure.entities.TaskEntity;
import com.moveapps.management.user.infraestructure.adapters.UserAdapter;
@Component
public class TaskAdapter extends EndPointGenericAdapterImpl<TaskDTO, TaskEntity> implements ITaskAdapter{
	@Autowired
	UserAdapter userAdapter;
	@Autowired
	StatusTaskAdapter statusTaskAdapter;
	@Override
	public TaskEntity toEntity(TaskDTO taskDTO) {
		return new TaskEntity().builder()
				.id(taskDTO.getId())
				.userEntity(taskDTO.getUserDTO() != null ? userAdapter.toEntity(taskDTO.getUserDTO()) : null)
				.statusTaskEntity(taskDTO.getStatusTaskDTO() != null ? statusTaskAdapter.toEntity(taskDTO.getStatusTaskDTO()) : null)
				.build();
	}

	@Override
	public String nameModule() {
		return MANAGEMENT_MODULE;
	}

	@Override
	public String className() {
		return TASK_ADAPTER;
	}

	@Override
	public TaskDTO toModelDTO(TaskEntity entity) {
		TaskDTO taskDTO = new TaskDTO().builder()
									.id(entity.getId())
									.userDTO(entity.getUserEntity() != null ? userAdapter.toModelDTO(entity.getUserEntity()) : null)
									.statusTaskDTO(entity.getStatusTaskEntity() != null ? statusTaskAdapter.toModelDTO(entity.getStatusTaskEntity()) : null)
									.build();
		taskDTO.setStatus(entity.getStatus());
		return taskDTO;
	}

}
