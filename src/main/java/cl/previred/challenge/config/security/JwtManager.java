package cl.previred.challenge.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtManager {

    private static final long EXPIRATION_TIME_SECONDS = 60L * 60L;

    @Value("${challenge.security.jwt-signing-key}")
    private String signingKey;

    public String generateToken(SecureUserDetails userDetails) {

        return Jwts.builder()
            .setClaims(buildClaims(userDetails))
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_SECONDS * 1000))
            .setIssuer(userDetails.getUsername())
            .signWith(SignatureAlgorithm.HS512, signingKey)
            .compact();

    }

    public JwtUserData parseJwtUserData(String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
        Claims claims = jws.getBody();
        return buildJwtUserDataFunction(claims);
    }


    private JwtUserData buildJwtUserDataFunction(Map<String, Object> claims) {
        String username = (String) claims.get("username");
        String role = (String) claims.get("role");
        return new JwtUserData(username, role);
    }

    private static Map<String, Object> buildClaims(SecureUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("role", userDetails.getRol().toUpperCase());
        return claims;
    }
}
