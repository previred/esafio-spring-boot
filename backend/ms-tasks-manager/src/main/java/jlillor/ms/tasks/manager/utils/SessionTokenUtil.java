package jlillor.ms.tasks.manager.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jlillor.ms.tasks.manager.properties.SessionTokenProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Utilidades para el manejo de tokens de sesión.
 *
 * @author Juan Lillo
 * @version 1.0
 * @see SessionTokenProperty
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class SessionTokenUtil {

    /** Propiedades de los tokens de sesión. */
    private final SessionTokenProperty properties;

    // -----------------------------------------------------------------------------------------
    // -- Métodos públicos ---------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * Genera un token de sesión.
     *
     * @param email {@link String} email del usuario
     * @return token {@link String} token generado
     */
    public String generateToken(final String email) {
        final var now = new Date();
        final var expiration = new Date(now.getTime() + (properties.getLifeTime() * 1000));
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(email)
                .setIssuedAt(now)
                .setId(UUID.randomUUID().toString())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, properties.getKey())
                .compact();
    }

    /**
     * Valida un token.
     *
     * @param token {@link String} token a validar
     * @param email {@link String} email del usuario
     * @return {@link Boolean} true si el token es válido, false en caso contrario
     */
    public boolean validateToken(final String token, final String email) {
        try {
            final var claims = Jwts.parser()
                    .setSigningKey(properties.getKey())
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject().equals(email) &&
                    !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene el email de un token.
     *
     * @param token {@link String} token a validar
     * @return {@link String} email del usuario
     */
    public String getEmail(final String token) {
        try {
            final var claims = Jwts.parser()
                    .setSigningKey(properties.getKey())
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

}
