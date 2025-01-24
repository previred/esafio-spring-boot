package cl.gestion.tarea.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:gestiondetareas")
                .username("sa")
                .password("")
                .driverClassName("org.h2.Driver")
                .build();
    }
}
