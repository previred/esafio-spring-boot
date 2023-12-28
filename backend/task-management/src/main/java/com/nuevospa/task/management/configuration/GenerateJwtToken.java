package com.nuevospa.task.management.configuration;

import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class GenerateJwtToken {
	

	private final static String secretKey = "keyToken$";
	

	private final static long expToken = 360000;
	

	public static String generateToken(String email) {
		
		Date now = new Date();
	    Date expiration = new Date(now.getTime() + expToken);
	
		
		return Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setSubject(email)
				.setIssuedAt(now)
				.setId(UUID.randomUUID().toString())
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
 

	public static boolean validateToken(String token, String email) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(token)
					.getBody();
			
			boolean validToken = claims.getSubject().equals(email) && 
					!isTokenExpired(token);
			
			return validToken;
			
		} catch (Exception e) {
			
			return false;
		}
	}
	
	 private static boolean isTokenExpired(String token) {
	        Date expiryDate = Jwts.parser()
	                .setSigningKey(secretKey)
	                .parseClaimsJws(token)
	                .getBody()
	                .getExpiration();
	        return expiryDate.before(new Date());
	 }
	 
	 
	
	 
	 public static String getEmailToken(String token) {
		    try {
		        Claims claims = Jwts.parser()
		                .setSigningKey(secretKey)
		                .parseClaimsJws(token)
		                .getBody();

		        return claims.getSubject();
		    } catch (Exception e) {
		        return null;
		    }
		}
	 
	

}
