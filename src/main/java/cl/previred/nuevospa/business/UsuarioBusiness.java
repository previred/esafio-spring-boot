package cl.previred.nuevospa.business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cl.previred.nuevospa.business.mappers.UsuarioMapper;
import cl.previred.nuevospa.dto.CredentialsDto;
import cl.previred.nuevospa.dto.LoginDto;
import cl.previred.nuevospa.dto.UsuarioDto;
import cl.previred.nuevospa.entities.Usuario;
import cl.previred.nuevospa.exceptions.ElementoNoEncontradoException;
import cl.previred.nuevospa.repository.UsuarioRepository;
import cl.previred.nuevospa.utils.ConstantsUtils;

@Service
public class UsuarioBusiness {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDto obtenerUsuarioPorId(Integer idUsuario) throws ElementoNoEncontradoException{
        Usuario usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if(usuarioEncontrado == null){
            throw new ElementoNoEncontradoException(Usuario.class, idUsuario.toString());
        }
        return UsuarioMapper.usuarioToUsuarioDto(usuarioEncontrado);
    }

    public List<UsuarioDto> obtenerUsuarios() throws ElementoNoEncontradoException{
        List<Usuario> usuariosEncontrados = usuarioRepository.findAll();
        if(usuariosEncontrados.isEmpty()){
            throw new ElementoNoEncontradoException(Usuario.class);
        }

        List<UsuarioDto> usuariosDto = usuariosEncontrados.stream()
                .map(usuario -> UsuarioMapper.usuarioToUsuarioDto(usuario))
                .collect(Collectors.toList());
        return usuariosDto;
    }

    public CredentialsDto login(LoginDto credenciales) throws ElementoNoEncontradoException, IllegalArgumentException{
        String passwordMD5 = convertirAHashMD5(credenciales.getPassword());
        Usuario usuarioEncotrado = usuarioRepository.findByUsername(credenciales.getUsername());
        if(usuarioEncotrado == null){
            throw new ElementoNoEncontradoException(Usuario.class, credenciales.getUsername());
        }
        if(!usuarioEncotrado.getPassword().equals(passwordMD5)){
            throw new IllegalArgumentException("Las credenciales proporcionadas son incorrectas");
        }

        String token = JWT.create()
                .withSubject(usuarioEncotrado.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                .withIssuer("previred")
                .sign(Algorithm.HMAC256(ConstantsUtils.SECRET_JWT)); 
        
        return new CredentialsDto(token);
    }


    private  static String convertirAHashMD5(String input) {
        try {
            byte[] messageDigest = MessageDigest.getInstance("MD5").digest(input.getBytes());

            StringBuilder hashText = new StringBuilder();
            for (byte b : messageDigest) {
                hashText.append(String.format("%02x", b));
            }

            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
