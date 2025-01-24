package cl.rreyes.nuevospa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import cl.rreyes.nuevospa.filter.JwtAuthFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	/*
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf(csrf -> csrf.disable()) // Disable CSRF
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/auth/login").permitAll() // Allow public access to login
	                .anyRequest().authenticated() // All other requests require authentication
	            );
	        return http.build();
	    }*/
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())  // Deshabilitar CSRF (Cross-Site Request Forgery) ya que estamos trabajando con JWT
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/auth/**").permitAll()  // Permitir rutas de autenticación sin JWT
	            .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll() // permitir acceso a swagger
	            .anyRequest().authenticated()  // El resto de las rutas requieren autenticación
	        )
	        .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);  // Agregar el filtro JWT antes del filtro de autenticación

	    return http.build();
	}
	/*
	@Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	     http.csrf(csrf -> csrf.disable())
	         .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
	     return http.build();
	 }*/
	
	 @Bean
	    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder = 
	            http.getSharedObject(AuthenticationManagerBuilder.class);
	        return authenticationManagerBuilder.build();
	    } 
	
	 
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
