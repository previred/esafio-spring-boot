package cl.gestion.tarea;

import cl.gestion.tarea.controller.TareaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GestionTareaServicioApplicationTests {

	@Autowired
	private TareaController controller;
	@Test
	void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}


}
