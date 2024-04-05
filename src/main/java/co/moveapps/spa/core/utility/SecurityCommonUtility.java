package co.moveapps.spa.core.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.owasp.encoder.Encode;

import java.security.*;
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
        return this.encodeWithMD5(text);
    }

    public String encodeWithMD5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());

            byte[] hashedBytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes)
                sb.append(String.format("%02x", b));

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public String generateJWT(Map<String, Object> claims, String secret, Long expirationTimeMinutes) {
        return Jwts.builder()
                .setAudience(JWT_AUDIENCE_ID)
                .addClaims(Optional.ofNullable(claims).orElse(new HashMap<>()))
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expirationTimeMinutes).toInstant()))
                .signWith(this.getSignInKey(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims verifyJWT(String token, String secret) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSignInKey(secret))
                .requireAudience(JWT_AUDIENCE_ID)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(String secret) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}