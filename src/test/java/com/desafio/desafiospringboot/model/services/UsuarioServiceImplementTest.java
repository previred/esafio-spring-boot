package com.desafio.desafiospringboot.model.services;

import com.desafio.desafiospringboot.model.dao.Usuario;
import com.desafio.desafiospringboot.model.repositories.UsuarioRepositorioJPA;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplementTest {
    @Mock
    UsuarioRepositorioJPA usuarioRepositorioJPA;
    @InjectMocks
    UsuarioServiceImplement usuarioServiceImplement;

    @Test
    void buscarUser() {
        Usuario usuarioRegistrado= DatosDePrueba.JUAN;
        when(usuarioRepositorioJPA.buscarUsuario("juan@prueba.com","JuanRod1&")).thenReturn(Optional.of(DatosDePrueba.JUAN));
        Usuario usuarioVerificado= usuarioServiceImplement.buscarUser(usuarioRegistrado);
        assertNotNull(usuarioVerificado);
        assertEquals(1L,usuarioVerificado.getId());

    }
}