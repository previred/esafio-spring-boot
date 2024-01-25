package com.spa.task.config;

import com.spa.task.audit.CustomAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "aware")
public class JpaConfig {

    @Bean(name = "aware")
    public AuditorAware<String> aware(){
        return new CustomAuditorAware();

    }

}
