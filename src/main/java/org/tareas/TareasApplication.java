package org.tareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.tareas.security.JWTAuthorizationFilter;


@SpringBootApplication
public class TareasApplication {
    public static void main(String[] args) {

        SpringApplication.run(TareasApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        public static final String[] SWAGGER_AUTH_WHITELIST = new String[]{"/**/swagger-ui.html", "/**/webjars/**", "/**/swagger-resources", "/**/swagger-resources/**", "/**/v2/api-docs", "/**/configuration/ui", "/**/configuration/security", "/**/swagger-ui/**", "/**/v3/api-docs/**"};
        // this one ignores under spring security
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/user").permitAll()
                    .antMatchers(SWAGGER_AUTH_WHITELIST).permitAll()
                    .anyRequest().authenticated();

        }

    }
}

