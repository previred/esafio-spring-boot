package cl.gestion.tarea.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DataSource dataSource;

    public DataInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        // Ruta del archivo data.sql dentro del classpath
        String dataSqlPath = "data.sql";

        // Cargar el archivo data.sql del classpath
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dataSqlPath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            // Leer el contenido del archivo línea por línea y ejecutar cada sentencia SQL
            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("--")) { // Ignorar líneas vacías o comentarios
                    sql.append(line);
                    if (line.endsWith(";")) { // Ejecutar cuando se completa una sentencia
                        statement.execute(sql.toString());
                        sql.setLength(0); // Limpiar el buffer para la siguiente sentencia
                    }
                }
            }

            System.out.println("Archivo data.sql ejecutado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al ejecutar data.sql: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
