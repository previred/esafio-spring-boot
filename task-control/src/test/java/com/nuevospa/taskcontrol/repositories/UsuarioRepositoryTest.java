package com.nuevospa.taskcontrol.repositories;

import com.nuevospa.taskcontrol.dtos.entities.Tarea;
import com.nuevospa.taskcontrol.dtos.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void saveUser_shouldSaveUser() {
        //Arrange
        Usuario usuario = mockUser();
        //Act
        Usuario savedUser = usuarioRepository.save(usuario);
        //Assert
        assertNotNull(savedUser);
    }

    @Test
    public void saveUserWithTasks_shouldSaveUser() {
        //Arrange
        Usuario usuario = mockUser();
        Tarea tarea = new Tarea();
        tarea.setNombre("Registro ingreso");
        tarea.setDescripcion("Se registra ingreso a oficina");
        tarea.setUsuario(usuario);
        usuario.addTarea(tarea);
        //Act
        Usuario savedUser = usuarioRepository.save(usuario);
        //Assert
        assertEquals(savedUser.getNombre(), usuario.getNombre());
        assertEquals(1, savedUser.getTareas().size());
    }

    @Test
    public void findUserByUsernameAndPass_shouldFindUser() {
        //Arrange
        Usuario usuario = mockUser();
        String username = "Pedro García";
        String pass = "a2asfGfdfdf4";
        //Act
        Usuario savedUser = usuarioRepository.save(usuario);
        Optional<Usuario> foundUser = usuarioRepository.findByNombreAndClave(username, pass);
        //Assert
        assertNotNull(foundUser);
        assertEquals(savedUser.getNombre(), foundUser.get().getNombre());
    }

    private Usuario mockUser() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Pedro García");
        usuario.setClave("a2asfGfdfdf4");
        return usuario;
    }
}
