package com.move.challenge.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.move.challenge.dto.JwtTokenDto;
import com.move.challenge.entity.UsuarioEntity;
import com.move.challenge.utils.exception.ChallengeExceptionCode;
import com.move.challenge.utils.exception.InvalidCredentialsException;

@Component
public class JwtUtils {

   @Value("${jwt.secret}")
   private String jwtSecret;

   @Value("${jwt.expiration}")
   private int jwtExpirationMs;

   public JwtTokenDto generateJwtToken(UsuarioEntity usuarioEntity) {

      Map<String, Object> claims = new HashMap<>();
      claims.put("subject", usuarioEntity.getNombre());
      claims.put("password", usuarioEntity.getClave());
      claims.put("email", usuarioEntity.getEmail());

      Claims jwtClaims = Jwts.claims(claims);
      jwtClaims.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs));
      jwtClaims.setIssuedAt(new Date());

      return new JwtTokenDto(Jwts
            .builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact());
   }

   public boolean validateJwtToken(String authToken) {
      try {
         Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
         return true;
      } catch (Exception e) {
         e.printStackTrace();
      }
      throw new InvalidCredentialsException(ChallengeExceptionCode.UNAUTHORIZED);
   }


}
