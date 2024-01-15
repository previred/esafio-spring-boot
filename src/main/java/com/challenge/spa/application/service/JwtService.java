package com.challenge.spa.application.service;

import com.challenge.spa.application.port.out.jwt.JwtPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService implements JwtPort {

  private static final String SECRET_KEY = "fb433b8eede76f6cf3bbadf55fd4a64fb0ea5937278fc43b80de0a814eb1a594";

  @Override
  public String getToken(UserDetails user) {
    return getToken(new HashMap<>(), user);
  }

  @Override
  public String getUsername(String token) {
    return getClaim(token, Claims::getSubject);
  }

  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username=getUsername(token);
    return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
  }
  public String getToken(Map<String, Object> extraClaims, UserDetails user) {
    Date now = new Date(System.currentTimeMillis());
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime()*1000*60*24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  private Key getKey() {
    byte[] keyBytes  = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private Claims getAllClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Date getExpiration(String token) {
    return getClaim(token, Claims::getExpiration);
  }

  private boolean isTokenExpired(String token) {
    return getExpiration(token).before(new Date());
  }
}
