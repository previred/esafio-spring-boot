package cl.previred.gestion.dto;

public class TaskResponse {
    private Long id;
    private String description;
    private String status;
    private String username;
    private String email;

    public TaskResponse(Long id, String description, String status, String username, String email) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

