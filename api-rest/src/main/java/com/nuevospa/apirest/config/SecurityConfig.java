package com.nuevospa.apirest.config;

import com.nuevospa.apirest.jwt.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		 String[] publicUrl = { "/swagger-ui/**",					//http://localhost:8080/swagger-ui/index.html   ---"/swagger-ui.html","/doc/swagger-ui.html","/doc/**",
				 				"/v3/api-docs/**",
							    "/auth/**",							//http://localhost:8080/auth/register
				 				"/h2/**" };							//http://localhost:8080/h2/h2-ui/

		return http
			.csrf(csrf ->
				csrf
					.disable())
			.authorizeHttpRequests(authRequest ->
				authRequest
					.requestMatchers(publicUrl).permitAll()
					//.requestMatchers("/**").permitAll()	//--> activar para permitir to do
					.anyRequest().authenticated()
			)
			.sessionManagement(sessionManager->
				sessionManager
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authProvider)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.build();

	}

}