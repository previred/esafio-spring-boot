package cl.nuevo.spa.taskmanager.configuration.openapi;

import cl.nuevo.spa.taskmanager.TaskManagerApplication;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The type Open api config. */
@Configuration
@AllArgsConstructor
@ConditionalOnProperty(value = "springdoc.swagger-ui.enabled", havingValue = "true")
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer")
public class OpenApiConfig {

  private static final String PATH_TO_SCAN = TaskManagerApplication.class.getPackageName();

  private static final String API_GROUP = "task-manager";

  private final OpenApiInfoProps props;

  /**
   * Customize open api open api.
   *
   * @return the open api
   */
  @Bean
  public OpenAPI customizeOpenAPI() {
    final String securitySchemeName = "bearerAuth";
    return new OpenAPI()
        .info(
            new Info()
                .title(this.props.getTitle())
                .description(this.props.getDescription())
                .version(this.props.getVersion()));
  }

  /**
   * Added specific configuration to share in openAPI web API.
   *
   * @return {@link GroupedOpenApi}
   */
  @Bean
  public GroupedOpenApi groupedOpenApi() {
    return GroupedOpenApi.builder().group(API_GROUP).packagesToScan(PATH_TO_SCAN).build();
  }
}
