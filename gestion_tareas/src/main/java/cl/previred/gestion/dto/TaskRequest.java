package cl.previred.gestion.dto;

public class TaskRequest {
    private String description;
    private String status; // El estado debe coincidir con un valor válido en `TaskStatus`

    public TaskRequest() {}

    public TaskRequest(String description, String status) {
        this.description = description;
        this.status = status;
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

    @Override
    public String toString() {
        return "TaskRequest{" +
                "description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

