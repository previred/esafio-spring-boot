package co.com.task.api.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfig {

    @Bean
    Jackson2ObjectMapperBuilderCustomizer jacksonConfigBuild() {
	return jacksonMapperBuilder -> jacksonMapperBuilder
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
		.serializationInclusion(JsonInclude.Include.NON_NULL)
                .modules(new Jdk8Module(), new JavaTimeModule());
    }
}
