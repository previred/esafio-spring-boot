package cl.previred.taskmanager.dto;

import java.time.LocalDate;

public class TaskResponse {

    private Long id;
    private Long userId;
    private String userName;
    private Long taskStatusId;
    private String taskStatusName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getTaskStatusId() {
		return taskStatusId;
	}
	public void setTaskStatusId(Long taskStatusId) {
		this.taskStatusId = taskStatusId;
	}
	public String getTaskStatusName() {
		return taskStatusName;
	}
	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
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
