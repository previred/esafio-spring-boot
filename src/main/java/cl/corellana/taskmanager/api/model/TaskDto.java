package cl.corellana.taskmanager.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskDto {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("status")
    private TaskStatus status = null;
}
