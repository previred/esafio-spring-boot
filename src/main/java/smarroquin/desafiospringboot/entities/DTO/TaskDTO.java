package smarroquin.desafiospringboot.entities.DTO;

import lombok.Data;

@Data
public class TaskDTO {
	
	private long id;
	private String status;
	private String name;
	private String description;

}