package co.moveapps.spa.core.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * CredentialsResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-04T07:43:43.981178-05:00[America/Bogota]")
public class CredentialsResponse {

  private String email;

  private String accessToken;

  private Integer expirationTime;

  public CredentialsResponse email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  
  @Schema(name = "email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public CredentialsResponse accessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  /**
   * Get accessToken
   * @return accessToken
  */
  
  @Schema(name = "access_token", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("access_token")
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public CredentialsResponse expirationTime(Integer expirationTime) {
    this.expirationTime = expirationTime;
    return this;
  }

  /**
   * Get expirationTime
   * @return expirationTime
  */
  
  @Schema(name = "expiration_time", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expiration_time")
  public Integer getExpirationTime() {
    return expirationTime;
  }

  public void setExpirationTime(Integer expirationTime) {
    this.expirationTime = expirationTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CredentialsResponse credentialsResponse = (CredentialsResponse) o;
    return Objects.equals(this.email, credentialsResponse.email) &&
        Objects.equals(this.accessToken, credentialsResponse.accessToken) &&
        Objects.equals(this.expirationTime, credentialsResponse.expirationTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, accessToken, expirationTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CredentialsResponse {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
    sb.append("    expirationTime: ").append(toIndentedString(expirationTime)).append("\n");
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

