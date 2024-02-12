package org.openapitools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class OpenapiGeneratorMavenPluginApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiGeneratorMavenPluginApplication.class, args);
    }
}
