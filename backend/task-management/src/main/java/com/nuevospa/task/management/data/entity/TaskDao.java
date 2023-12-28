package com.nuevospa.task.management.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nuevospa.task.management.dto.task.Priority;

import lombok.Data;

@Entity
@Data
@Table(name="tareas")
public class TaskDao {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", nullable = false)
	private String title;
	
	private String description;
	
	@Column(name="startDate", nullable = false)
	private LocalDateTime startDate;
	private LocalDateTime finishDate;
	
	@Column(name="priority", nullable = false)
	@Enumerated(EnumType.STRING)
	private Priority priority;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserDao user;
	
	@ManyToOne
	@JoinColumn(name = "task_id", referencedColumnName = "id")
	private TaskStatusDao status;

}
