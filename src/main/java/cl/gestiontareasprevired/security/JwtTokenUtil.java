package cl.gestiontareasprevired.security;

import cl.gestiontareasprevired.model.Usuarios;
import cl.gestiontareasprevired.repository.UsuariosRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureException;


import java.util.Date;
import java.util.Optional;


@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public String generateJwtToken(Usuarios users) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setSubject(users.getEmail())
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + 3600000)) // Expira en 1 hora
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateTokenExpiration(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateTokenAndUser(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            String email = claims.getSubject();
            if(email == null || email.isEmpty()) {
                return false;
            }
            Optional<Usuarios> usuario = usuariosRepository.findByEmail(email);
            return validateTokenExpiration(token) && usuario.isPresent();
        } catch (SignatureException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
