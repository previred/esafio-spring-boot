package com.nuevospa.gestiontareas.service.impl;

import com.nuevospa.gestiontareas.data.UsuariosRepository;
import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import com.nuevospa.gestiontareas.model.security.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UsuariosServiceImplTest {
    @Mock
    private UsuariosRepository usuariosRepository;
    @InjectMocks
    private UsuariosServiceImpl usuariosService;

    @Test
    void obtenerTodosLosUsuariosTest() {
        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(Usuario.builder().nombre("adela").email("adela@adela.com").password("adela1").build());
        usuarios.add(Usuario.builder().nombre("francia").email("francia@francia.com").password("francia1").build());

        given(usuariosRepository.findAll()).willReturn(new ArrayList<>(usuarios));

        Set<UsuarioDTO> usrs = usuariosService.obtenerTodosLosUsuarios();

        assertNotNull(usrs);
        assertEquals(usrs.size(), usrs.size());
    }

    @Test
    void obtenerUsuarioPorEmailTest() {
        Optional<Usuario> usr =
            Optional.ofNullable(
                Usuario.builder()
                    .nombre("adela")
                    .email("adela@adela.com")
                    .password("adela1")
                .build());

        given(usuariosRepository.findByEmail("email")).willReturn(usr);

        UsuarioDTO usrDto = usuariosService.obtenerUsuarioPorEmail("email");

        assertNotNull(usrDto);
        assertEquals(usrDto.getEmail(), usr.get().getEmail());
        assertEquals(usrDto.getNombre(), usr.get().getNombre());
    }
}
