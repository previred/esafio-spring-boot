package co.api.gestiontareas.domain.service.impl;

import co.api.gestiontareas.domain.model.common.MensajeDTO;
import co.api.gestiontareas.domain.model.user.User;
import co.api.gestiontareas.domain.model.user.repository.UserGateway;
import co.api.gestiontareas.domain.service.AuthService;
import co.api.gestiontareas.infrastructure.helpers.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserGateway userGateway;

    private final JWTUtil jwtUtil;

    @Override
    public String login(String username, String password) throws Exception {

        if(!userGateway.existByUsername(username)){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User client = userGateway.getUser(username);

        if( !passwordEncoder.matches(password, client.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rol", "CLIENT");
        map.put("username", client.getUsername());
        map.put("id", client.getId());

        return jwtUtil.generateToken(String.valueOf(client.getId()), map);
    }

    @Override
    public User createUser(String username, String password) throws Exception {
        if(userGateway.existByUsername(username))
            throw new Exception("El username ya existe");

        return userGateway.save(
                User.builder()
                        .username(username)
                        .password(jwtUtil.encritarPassword(password))
                        .build()
        );
    }
}
