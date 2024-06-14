package com.desafio.spring.ec.ws;

import com.desafio.spring.ec.bs.utils.GenericConverterUtils;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.desafio.spring.ec.ws", "com.desafio.spring.ec.bs", "com.desafio.spring.ec.ds"})
@EntityScan(basePackages = {"com.desafio.spring.ec.ds.entity"})
@EnableJpaRepositories(basePackages = {"com.desafio.spring.ec.bs.repository"})
public class ServiceConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public GenericConverterUtils genericConverterUtils(ModelMapper modelMapper) {
        return new GenericConverterUtils(modelMapper);
    }
}
