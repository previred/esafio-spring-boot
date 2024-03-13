package cl.gestiontareasprevired.service;

import cl.gestiontareasprevired.dto.LoginCredentials;
import cl.gestiontareasprevired.dto.LoginSuccessResponse;
import cl.gestiontareasprevired.model.Usuarios;
import cl.gestiontareasprevired.repository.UsuariosRepository;
import cl.gestiontareasprevired.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public Optional<LoginSuccessResponse> validarUsuario(LoginCredentials loginCredentials) {
        Optional<Usuarios> usuario = usuariosRepository.findByEmailAndPassword(loginCredentials.getMail(), loginCredentials.getPass());
        if (usuario.isPresent()) {
            Usuarios user = usuario.get();
            String token = jwtTokenUtil.generateJwtToken(user);
            LoginSuccessResponse response = new LoginSuccessResponse(true, token, "Usuario autenticado exitosamente.");
            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

}
