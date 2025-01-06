package cl.previred.taskmanagement.application.service;

import cl.previred.taskmanagement.application.dto.response.RespuestaTokenDTO;
import cl.previred.taskmanagement.core.domain.constant.CodigosEnum;
import cl.previred.taskmanagement.core.domain.entities.Usuario;
import cl.previred.taskmanagement.core.domain.exception.CredencialesIncorrectasException;
import cl.previred.taskmanagement.core.domain.exception.CustomException;
import cl.previred.taskmanagement.core.domain.exception.TokenException;
import cl.previred.taskmanagement.core.domain.exception.UsuarioNoEncontradoException;
import cl.previred.taskmanagement.core.port.UsuarioRepository;
import cl.previred.taskmanagement.core.port.UsuarioRolRepository;
import cl.previred.taskmanagement.core.service.AuthService;
import cl.previred.taskmanagement.infrastructure.config.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Override
    @Transactional
    public RespuestaTokenDTO login(String username, String password) {
        RespuestaTokenDTO respuestaTokenDTO = new RespuestaTokenDTO();
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        if (password.equals(usuario.getPassword())) {

            try {
                List<String> roles = usuarioRolRepository.findByUsuarioRut(usuario.getRut()).stream()
                        .map(usuarioRol -> usuarioRol.getRol().getCodigo())
                        .collect(Collectors.toList());

                String token = jwtUtil.generateToken(username, roles);

                respuestaTokenDTO.setToken(jwtUtil.generateToken(username, roles));
                respuestaTokenDTO.setCodigo(CodigosEnum.EXITO.getCode());
                respuestaTokenDTO.setDescripcion(CodigosEnum.EXITO.getMessage());

                return respuestaTokenDTO;
            }
            catch (DataAccessException e){
                throw new CustomException("Error de acceso de datos al obtener los roles del usuario");
            }
            catch (NullPointerException e) {
                throw new CustomException("Error: uno de los roles del usuario es nulo");
            }
            catch (Exception e) {
                throw new CustomException("Error: Error no controlado");
            }

        } else {
            throw new CredencialesIncorrectasException("Credenciales incorrectas");
        }
    }

    @Override
    public RespuestaTokenDTO validateToken(String token) {
        RespuestaTokenDTO respuestaTokenDTO = new RespuestaTokenDTO();
        try {
            String username = jwtUtil.extractClaims(token).getSubject();
            Boolean esValido = jwtUtil.validateToken(token, username);
            respuestaTokenDTO.setCodigo(CodigosEnum.TOKEN_VALIDO.getCode());
            respuestaTokenDTO.setDescripcion(CodigosEnum.TOKEN_VALIDO.getMessage());
            return respuestaTokenDTO;
        } catch (ExpiredJwtException e) {
            throw new TokenException("El token ha expirado");
        } catch (MalformedJwtException e) {
            throw new TokenException("El token está mal formado");
        } catch (SignatureException e) {
            throw new TokenException("La firma del token no es válida");
        } catch (IllegalArgumentException e) {
            throw new TokenException("El token es nulo o vacío");
        } catch (Exception e) {
            throw new TokenException("Error inesperado al validar el token");
        }
    }
}
