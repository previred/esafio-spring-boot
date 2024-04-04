package co.moveapps.spa.core.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.owasp.encoder.Encode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.*;

public class SecurityCommonUtility {

    private final String JWT_AUDIENCE_ID = "x-nuevo-spa";

    private static SecurityCommonUtility commonsUtility;

    public static SecurityCommonUtility getInstance() {
        return Optional.ofNullable(commonsUtility).orElse(new SecurityCommonUtility());
    }

    public boolean isSanitized(final String string) {
        if (string == null)
            return false;
        final String sanitizedHTML = Encode.forHtml(string);
        return sanitizedHTML.equals(string);
    }

    public String encode(String text) {
        return this.encodeWithBcryptHashing(text);
    }

    private String encodeWithBcryptHashing(String text) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(text);
    }

    public String generateJWT(Map<String, Object> claims, String secret, Long expirationTimeMinutes) {
        final Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        return Jwts.builder()
                .setAudience(JWT_AUDIENCE_ID)
                .addClaims(Optional.ofNullable(claims).orElse(new HashMap<>()))
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expirationTimeMinutes).toInstant()))
                .signWith(key, SignatureAlgorithm.ES256)
                .compact();
    }

    public Claims verifyJWT(String token, String secret) {
        final Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .requireAudience(JWT_AUDIENCE_ID)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}