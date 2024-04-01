package cl.previred.desafip.msprevireddesafio.services;

import cl.previred.desafip.msprevireddesafio.security.JwtAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private static final String SECRET = "prvrd";
    private static final long EXPIRATION_TIME = 900_000;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET));
    }

    public String getUsernameFromToken(String token) {
        try {
        System.out.println(token);
        String username = JWT.require(Algorithm.HMAC512(SECRET))
                .build()
                .verify(token)
                .getSubject();
            return username;

        } catch (JWTDecodeException e) {
            logger.error("Error decodificando el token JWT: {}", e.getMessage());
        }
        return token;
    }
}