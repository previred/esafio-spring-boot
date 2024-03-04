package cl.previred.desafio.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    @Autowired
    private DataSource dataSource;

    @Value("classpath:sql/tables.sql")
    private Resource schemaScript;

    @Value("classpath:sql/inserts.sql")
    private Resource dataScript;

    @PostConstruct
    public void init() throws IOException, SQLException {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, schemaScript);
            ScriptUtils.executeSqlScript(connection, dataScript);
        }
    }
}
