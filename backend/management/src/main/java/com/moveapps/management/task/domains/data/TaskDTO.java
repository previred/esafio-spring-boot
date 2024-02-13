package com.moveapps.management.task.domains.data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.moveapps.management.statustask.domains.data.StatusTaskDTO;
import com.moveapps.management.statustask.infraestructure.entities.StatusTaskEntity;
import com.moveapps.management.user.domains.data.UserDTO;
import com.moveapps.management.user.infraestructure.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
	 private String id;
	 private UserDTO userDTO;
	 private StatusTaskDTO statusTaskDTO;
	 private String status;
}
