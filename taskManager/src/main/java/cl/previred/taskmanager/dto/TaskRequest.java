package cl.previred.taskmanager.dto;

import java.time.LocalDate;

public class TaskRequest {

    private Long id;
    private Long userId;
    private Long taskStatusId;
    private String description;
    private LocalDate dueDate;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTaskStatusId() {
		return taskStatusId;
	}
	public void setTaskStatusId(Long taskStatusId) {
		this.taskStatusId = taskStatusId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

    
}
