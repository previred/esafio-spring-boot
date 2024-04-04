package co.moveapps.spa.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MsCoreSpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCoreSpaApplication.class, args);
    }

}
