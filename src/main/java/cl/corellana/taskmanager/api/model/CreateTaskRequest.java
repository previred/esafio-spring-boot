package cl.corellana.taskmanager.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateTaskRequest {
    @JsonProperty("title")
    private String title = null;
}
