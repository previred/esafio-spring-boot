package com.moveapps.management.statustask.infraestructure.adapters;

import static com.moveapps.management.user.infraestructure.confing.Constants.MANAGEMENT_MODULE;
import static com.moveapps.management.user.infraestructure.confing.Constants.STATUSTASK_ADAPTER;

import org.springframework.stereotype.Component;

import com.moveapps.commons.api.infraestructure.adapters.EndPointGenericAdapterImpl;
import com.moveapps.management.statustask.domains.data.StatusTaskDTO;
import com.moveapps.management.statustask.infraestructure.entities.StatusTaskEntity;

@Component
public class StatusTaskAdapter extends EndPointGenericAdapterImpl<StatusTaskDTO, StatusTaskEntity> implements IStatusTaskAdapter{

	@Override
	public StatusTaskEntity toEntity(StatusTaskDTO statusTaskDTO) {
		return new StatusTaskEntity().builder()
				.id(statusTaskDTO.getId())
				.description(statusTaskDTO.getDescription())
				.build();
	}

	@Override
	public String nameModule() {
		return MANAGEMENT_MODULE;
	}

	@Override
	public String className() {
		return STATUSTASK_ADAPTER;
	}

	@Override
	public StatusTaskDTO toModelDTO(StatusTaskEntity entity) {
		StatusTaskDTO statusTaskDTO = new StatusTaskDTO().builder()
				.id(entity.getId())
				.description(entity.getDescription())
				.build();
		statusTaskDTO.setStatus(entity.getStatus());
		return statusTaskDTO;
	}

}
