package co.com.task.api.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import co.com.task.api.config.SecurityConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtilService {
	
    private SecurityConfig config;

    public JwtUtilService(SecurityConfig config) {
	this.config = config;
    }
	
    public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      return claimsResolver.apply(extractAllClaims(token));
    }

    @SuppressWarnings("deprecation")
    private Claims extractAllClaims(String token) {
	return Jwts.parser().setSigningKey(config.getSecret()).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
      Map<String, Object> claims = new HashMap<>();
      GrantedAuthority rol = userDetails.getAuthorities().stream().toList().get(0);
      claims.put("rol", rol);
      return createToken(claims, userDetails.getUsername());
    }

    @SuppressWarnings("deprecation")
    private String createToken(Map<String, Object> claims, String subject) {
	return Jwts
	          .builder()
	          .setClaims(claims)
	          .setSubject(subject)
	          .setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + config.getExpiration()))
		.signWith(SignatureAlgorithm.HS256, config.getSecret())
	          .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
}
