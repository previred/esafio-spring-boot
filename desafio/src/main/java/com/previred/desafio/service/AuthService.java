package com.previred.desafio.service;

import com.previred.desafio.entity.Usuario;
import com.previred.desafio.exception.GenericException;
import com.previred.desafio.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class AuthService {
    @Autowired
    public UsuarioRepository usuarioRepository;

    private static final String SECRET_KEY = "superSecretKeyThatIsAtLeast32BytesLong12345";

    public String generateToken(Usuario usuario) {
        Usuario user = usuarioRepository.
                findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword()).orElse(null);
        if(user==null){
            throw new GenericException(HttpStatus.FORBIDDEN, "Credenciales incorrectas");
        }
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 120000))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody().getExpiration().after(new Date());
        }catch (Exception e){
            throw new GenericException(HttpStatus.UNAUTHORIZED, "Credenciales expiradas");
        }

    }

    public String extractUsername(String token) {

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


}
