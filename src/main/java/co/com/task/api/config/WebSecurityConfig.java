package co.com.task.api.config;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.task.api.security.JwtRequestFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = { "/h2-console", "/h2-console/**", "/swagger-ui/**", "/swagger-ui",
	    "/swagger-resources/**", "/swagger-ui.html",
	    "/v2/api-docs",
	    "/webjars/**", "/authentication", "/authentication/**" };

    private UserDetailsService usuarioDetailsService;
    private JwtRequestFilter jwtRequestFilter;
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    public WebSecurityConfig(UserDetailsService usuarioDetailsService, JwtRequestFilter jwtRequestFilter,
	    JwtAuthenticationEntryPoint unauthorizedHandler) {
	this.usuarioDetailsService = usuarioDetailsService;
	this.jwtRequestFilter = jwtRequestFilter;
	this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.accessDeniedHandler(accessDeniedHandler()).and().headers().frameOptions().sameOrigin().and().csrf()
		.disable().authorizeRequests()
		.antMatchers(AUTH_WHITELIST).permitAll()
		.anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    AuthenticationFailureHandler failureHandler() {
	return (request, response, ex) -> {
	    throw ex;
	};
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
	return (request, response, ex) -> {
	    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

	    ServletOutputStream out = response.getOutputStream();
	    new ObjectMapper().writeValue(out, Map.of("mensaje", "HTTP ERROR 403 Forbidden"));
	    out.flush();
	};
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(usuarioDetailsService).passwordEncoder(passwordEncoder());
    }

}

