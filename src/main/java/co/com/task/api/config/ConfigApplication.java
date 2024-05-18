package co.com.task.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class ConfigApplication {

    @Value("${expresion-regular.email}")
    private String emailRegx;

    @Value("${expresion-regular.password}")
    private String passwordRegx;

}
