package com.nuevospa.gestiontareas.data;

import com.nuevospa.gestiontareas.model.security.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UsuariosRepositoryTest {
    @Autowired
    private UsuariosRepository usuariosRepository;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = Usuario.builder()
                .email("test@example.com")
                .nombre("Test User")
                .password("test123")
                .build();
        usuario = usuariosRepository.save(usuario);
    }

    @Test
    void findByEmailWhenExists() {
        Optional<Usuario> found = usuariosRepository.findByEmail("test@example.com");
        assertThat(found).isNotEmpty();
        found.ifPresent(usuario -> assertThat(usuario.getEmail()).isEqualTo("test@example.com"));
    }

    @Test
    void findByEmailWhenNotExists() {
        Optional<Usuario> found = usuariosRepository.findByEmail("notfound@example.com");
        assertThat(found).isEmpty();
    }

    @Test
    void findByIdWhenExists() {
        Optional<Usuario> found = usuariosRepository.findById(usuario.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get().getId()).isEqualTo(usuario.getId());
    }

    @Test
    void findByIdWhenNotExists() {
        Optional<Usuario> found = usuariosRepository.findById(999L);
        assertThat(found).isEmpty();
    }
}
