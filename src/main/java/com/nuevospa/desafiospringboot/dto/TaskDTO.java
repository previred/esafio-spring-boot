package com.nuevospa.desafiospringboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nuevospa.desafiospringboot.model.TaskStatus;
import com.nuevospa.desafiospringboot.model.User;

import lombok.Data;

@Data
public class TaskDTO {
	
	private int id;
	private String title;
	private String description;
	@JsonIgnore
	private TaskStatus status;
	private Integer statusId;
	@JsonIgnore
	private User user;
	private Integer userId;

}
