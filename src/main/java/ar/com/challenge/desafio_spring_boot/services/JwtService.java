package ar.com.challenge.desafio_spring_boot.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtService {
    String getToken(UserDetails user);
    String getUsernameFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    <T> T getClaim(String token, Function<Claims, T> claimsResolver);
}
