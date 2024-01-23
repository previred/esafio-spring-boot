package cl.nuevo.spa.taskmanager.configuration.openapi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("swagger")
class OpenApiInfoProps {

  private String version;

  private String title;

  private String description;
}
