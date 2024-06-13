package com.example.usertask.domain;

import jakarta.persistence.*;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String task,  registrationNumber;


	public Task() {
	}

	public Task(String task, String registrationNumber) {
		super();
		this.task = task;
		this.registrationNumber = registrationNumber;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


}
