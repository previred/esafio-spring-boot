package cl.nuevo.spa.taskmanager.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/** The type Jwt service. */
@Service
public class JwtService {

  private static final String JWT = "JWT";
  private final MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
  private final Decoder<CharSequence, byte[]> decoder = Decoders.BASE64;

  /** The Secret key. */
  @Value("${security.jwt.secret-key}")
  public String SECRET_KEY;

  /** The Expiration in minutes. */
  @Value("${security.jwt.expiration-in-minutes}")
  public int EXPIRATION_IN_MINUTES;

  /**
   * Generate token string.
   *
   * @param userDetails the user details
   * @param extraClaims the extra claims
   * @return the string
   */
  public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
    return Jwts.builder()
        .header()
        .type(JWT)
        .and()
        .subject(userDetails.getUsername())
        .issuedAt(new Date(Instant.now().toEpochMilli()))
        .expiration(
            new Date(
                Instant.now()
                    .plusSeconds(Duration.ofMinutes(EXPIRATION_IN_MINUTES).getSeconds())
                    .toEpochMilli()))
        .claims(extraClaims)
        .signWith(generatedKey(), macAlgorithm)
        .compact();
  }

  /**
   * Extract user name string.
   *
   * @param jwt the jwt
   * @return the string
   */
  public String extractUserName(String jwt) {
    return extractAllClaims(jwt).getSubject();
  }

  private Claims extractAllClaims(String jwt) {
    return Jwts.parser().verifyWith(generatedKey()).build().parseSignedClaims(jwt).getPayload();
  }

  private SecretKey generatedKey() {
    byte[] passwordDecoded = decoder.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(passwordDecoded);
  }
}
