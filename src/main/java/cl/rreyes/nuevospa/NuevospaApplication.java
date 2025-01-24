package cl.rreyes.nuevospa;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cl.rreyes.nuevospa.model.EstadoTarea;
import cl.rreyes.nuevospa.model.Usuario;
import cl.rreyes.nuevospa.service.EstadoTareaService;
import cl.rreyes.nuevospa.service.UsuarioService;
import jakarta.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "cl.rreyes.nuevospa")
public class NuevospaApplication {
	@Autowired
	private EstadoTareaService estadoTareaService;
	
	@Autowired
	private UsuarioService usuarioService;

   /* NuevospaApplication(EstadoTareaService estadoTareaService) {
        this.estadoTareaService = estadoTareaService;
    }
*/
	public static void main(String[] args) {
		SpringApplication.run(NuevospaApplication.class, args);
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(base64Key);
		
	}
	 @PostConstruct
	    public void precargarDatos() {
	        String[] estados = {"Pendiente", "En Progreso", "Completada", "Cancelada"};
	        for (String estado : estados) {	        	
	            EstadoTarea eTarea = new EstadoTarea();
	            eTarea.setDescripcion(estado);
	            estadoTareaService.createEstadoTarea(eTarea);
	        }
	        Usuario usuario = new Usuario("rreyes","abc123");
	        usuarioService.registerUser(usuario);
	        Usuario usuario2 = new Usuario("jdoe","abc123");
	        usuarioService.registerUser(usuario2);
	        Usuario usuario3 = new Usuario("peekabo","abc123");
	        usuarioService.registerUser(usuario3);
	        
	        
	    }

}
