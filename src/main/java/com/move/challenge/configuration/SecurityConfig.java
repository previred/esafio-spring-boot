package com.move.challenge.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private JwtAuthenticationFilter jwtAuthenticationFilter;

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable().authorizeRequests()
          // Configura tus endpoints aquí, por ejemplo:
          .antMatchers("/api/public/**", "/h2-console/**").permitAll()
          .antMatchers("/api/private/**").authenticated()
          .and()
          .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      // Configura la autenticación, por ejemplo, en memoria
      auth
            .inMemoryAuthentication()
            .withUser("usuario").password("{noop}password").roles("USER");
   }

}