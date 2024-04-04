package com.moveapps.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@Configuration
public class WebConfig {

    @Bean
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new DefaultHandlerExceptionResolver();
    }
}
