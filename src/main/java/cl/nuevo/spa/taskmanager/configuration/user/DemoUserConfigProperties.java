package cl.nuevo.spa.taskmanager.configuration.user;

import cl.nuevo.spa.taskmanager.domain.dto.UserRegistryDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@AllArgsConstructor
@ConditionalOnProperty(prefix = "demo-data", name = "enable", havingValue = "true")
@ConfigurationProperties(prefix = "demo-data")
@Data
public class DemoUserConfigProperties {

  private final List<UserRegistryDto> users;
}
