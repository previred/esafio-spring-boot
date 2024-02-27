package com.nuevospa.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private static String secret = "Uc+/QfLdhVqckdSpa3kOenmXcL5EmNRwod3aP/4K/N4=";

	public static <T> T obtieneClaims(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = obtieneTodo(token);
		return claimsResolver.apply(claims);

	}

	@SuppressWarnings("deprecation")
	public static Claims obtieneTodo(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public String obtenerUsuario(String token) {
		return obtieneClaims(token, Claims::getSubject);
	}

	public static Date extraerFecha(String token) {
		return obtieneClaims(token, Claims::getExpiration);
	}

	public static Boolean tokenExpirado(String token) {
		try {
			Date actual = new Date(System.currentTimeMillis());
			Date fecha = extraerFecha(token);
			return actual.before(fecha);
		} catch (ExpiredJwtException e) {
	        // El token ha expirado
	        return false;
	    }	
	}

	public static String generaToken(String nombre, String password) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("password", password);
		return crearToken(claims, nombre);
	}

	private static String crearToken(Map<String, Object> claims, String nombre) {

		Date fechaCreacion = new Date(System.currentTimeMillis());
		Date fechaVencimiento = new Date(System.currentTimeMillis() + 600000);

		return Jwts.builder().setClaims(claims).setSubject(nombre).setIssuedAt(fechaCreacion)
				.setExpiration(fechaVencimiento).signWith(SignatureAlgorithm.HS256, secret).compact();

	}

}
