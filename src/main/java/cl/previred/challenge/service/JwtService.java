package cl.previred.challenge.service;

import cl.previred.challenge.dto.AuthLoginPostRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

  private final String secret;
  private SecretKey secretKey;


  public JwtService(@Value("${auth.secret}") String secret) {
    this.secret = secret;
  }


  @PostConstruct
  public void init() {
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
  }

  public String generateToken(UserDetails userDetails) {
    long now = System.currentTimeMillis();
    long expirationTime = 1000 * 60 * 60; // 1 hora en milisegundos

    JwtBuilder jwt = Jwts.builder()
      .subject(userDetails.getUsername())
      .issuedAt(new Date(now))
      .expiration(new Date(now + expirationTime))
      .signWith(secretKey);

    jwt.claims().add("role", userDetails.getAuthorities());

    return jwt
      .compact();
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = Jwts.parser()
      .verifyWith(secretKey)
      .build()
      .parseSignedClaims(token)
      .getPayload();
    return claimsResolver.apply(claims);
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private Boolean isTokenExpired(String token) {
    return extractClaim(token, Claims::getExpiration).before(new Date());
  }
}
