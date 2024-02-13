package com.moveapps.management.statustask.infraestructure.adapters;

import com.moveapps.management.statustask.domains.data.StatusTaskDTO;
import com.moveapps.management.statustask.infraestructure.entities.StatusTaskEntity;

public interface IStatusTaskAdapter {
	StatusTaskEntity toEntity(StatusTaskDTO statusTaskDTO);
}
