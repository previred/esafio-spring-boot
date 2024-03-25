package smarroquin.desafiospringboot.authentication;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenConfiguration {
	
	private static final String SECRET_KEY = "v1GUpUaAID2RmO8bxunVc3PwLN2ghSyq";
	private static final long TOKEN_VALIDITY = 1200000;
	
	public static String generateToken(String user) {
		Date expiration = new Date(System.currentTimeMillis() + TOKEN_VALIDITY);
		
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", user);
		
		return Jwts.builder()
				.setSubject(user)
				.setExpiration(expiration)
				.addClaims(userInfo)
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
				.compact();
	}
	
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, Collections.emptyList());
	}
}