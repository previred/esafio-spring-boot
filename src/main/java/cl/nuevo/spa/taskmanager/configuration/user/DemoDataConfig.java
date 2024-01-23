package cl.nuevo.spa.taskmanager.configuration.user;

import cl.nuevo.spa.taskmanager.service.UserService;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@ConditionalOnProperty(prefix = "demo-data", name = "enable", havingValue = "true")
public class DemoDataConfig {

  private final UserService userService;
  private final DemoUserConfigProperties demoUserConfigProperties;

  @PostConstruct
  public void init() {
    demoUserConfigProperties.getUsers().forEach(userService::createUser);
  }
}
