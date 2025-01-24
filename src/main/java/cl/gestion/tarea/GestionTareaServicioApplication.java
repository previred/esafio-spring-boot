package cl.gestion.tarea;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionTareaServicioApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(GestionTareaServicioApplication.class, args);
	}

	public void foo() { throw new UnsupportedOperationException(); }
}
