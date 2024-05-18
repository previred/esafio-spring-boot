package co.com.task.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.Generated;

@EnableWebMvc
@SpringBootApplication
public class MicroserviceApplication {

    @Generated
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }
}
