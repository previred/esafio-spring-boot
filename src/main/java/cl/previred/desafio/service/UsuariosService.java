package cl.previred.desafio.service;

import cl.previred.desafio.dto.UsuariosDto;
import cl.previred.desafio.model.Usuarios;
public interface UsuariosService {

    UsuariosDto loadUserByUser(Usuarios user) throws Exception;

    void createNewUser(Usuarios user) throws Exception;
}
