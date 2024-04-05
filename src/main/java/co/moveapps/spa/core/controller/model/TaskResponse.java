package co.moveapps.spa.core.controller.model;

import co.moveapps.spa.core.model.entity.TaskEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

/**
 * TaskResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-05T04:39:24.553212-05:00[America/Bogota]")
public class TaskResponse {

  private UUID id;

  private StatusResponse status;

  private UserResponse user;

  private String title;

  private String description;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  public TaskResponse id(UUID id) {
    this.id = id;
    return this;
  }

  public TaskResponse(TaskEntity entity) {
    this.id = entity.getId();
    this.status = new StatusResponse(entity.getState());
    this.user = new UserResponse(entity.getUser());
    this.title = entity.getTitle();
    this.description = entity.getDescription();
    this.createdAt = entity.getCreatedAt().atZone(ZoneOffset.UTC).toOffsetDateTime();
    this.updatedAt = entity.getUpdatedAt().atZone(ZoneOffset.UTC).toOffsetDateTime();
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public TaskResponse status(StatusResponse status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public StatusResponse getStatus() {
    return status;
  }

  public void setStatus(StatusResponse status) {
    this.status = status;
  }

  public TaskResponse user(UserResponse user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @Valid 
  @Schema(name = "user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user")
  public UserResponse getUser() {
    return user;
  }

  public void setUser(UserResponse user) {
    this.user = user;
  }

  public TaskResponse title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  
  @Schema(name = "title", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TaskResponse description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskResponse createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @Valid 
  @Schema(name = "created_at", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("created_at")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public TaskResponse updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
  */
  @Valid 
  @Schema(name = "updated_at", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updated_at")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskResponse taskResponse = (TaskResponse) o;
    return Objects.equals(this.id, taskResponse.id) &&
        Objects.equals(this.status, taskResponse.status) &&
        Objects.equals(this.user, taskResponse.user) &&
        Objects.equals(this.title, taskResponse.title) &&
        Objects.equals(this.description, taskResponse.description) &&
        Objects.equals(this.createdAt, taskResponse.createdAt) &&
        Objects.equals(this.updatedAt, taskResponse.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, user, title, description, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

