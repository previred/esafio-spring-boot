package com.nuevospa.gestiontareas.mapper;

import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import com.nuevospa.gestiontareas.model.security.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UsuarioMapperTest {
    @Test
    void whenDtoToEntity_thenCorrectConversion() {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .id(1L)
                .email("test@example.com")
                .nombre("Test User")
                .build();

        Usuario usuario = UsuarioMapper.dtoToEntity(usuarioDTO);

        assertEquals(usuarioDTO.getId(), usuario.getId());
        assertEquals(usuarioDTO.getEmail(), usuario.getEmail());
        assertEquals(usuarioDTO.getNombre(), usuario.getNombre());
    }

    @Test
    void whenEntityToDto_thenCorrectConversion() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .email("test@example.com")
                .nombre("Test User")
                .build();

        UsuarioDTO usuarioDTO = UsuarioMapper.entityToDto(usuario);

        assertEquals(usuario.getId(), usuarioDTO.getId());
        assertEquals(usuario.getEmail(), usuarioDTO.getEmail());
        assertEquals(usuario.getNombre(), usuarioDTO.getNombre());
    }

    @Test
    void whenDtoToEntityWithNullDto_thenNullEntity() {
        assertNull(UsuarioMapper.dtoToEntity(null));
    }

    @Test
    void whenEntityToDtoWithNullEntity_thenNullDto() {
        assertNull(UsuarioMapper.entityToDto(null));
    }

    @Test
    void entityToUserDetails_DebeRetornarNull_CuandoUsuarioEsNull() {
        assertNull(UsuarioMapper.entityToUserDetails(null));
    }

    @Test
    void entityToUserDetails_DebeRetornarUserDetails_CuandoUsuarioEsValido() {
        // Preparar un usuario de prueba
        Usuario usr = new Usuario();
        usr.setEmail("test@example.com");
        usr.setPassword("password123");

        // Llamar al método bajo prueba
        UserDetails userDetails = UsuarioMapper.entityToUserDetails(usr);

        // Verificar que los UserDetails sean correctos
        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
    }
}
