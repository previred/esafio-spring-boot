package co.com.task.api.configtest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SuppressWarnings("deprecation")
@TestConfiguration
@EnableWebSecurity
@SpringBootApplication
public class ConfigApiRestTest extends WebSecurityConfigurerAdapter {

    @Bean
    JwtFilterTest authenticationJwtTokenFilter() {
	return new JwtFilterTest();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable).httpBasic(AbstractHttpConfigurer::disable)
		.formLogin(AbstractHttpConfigurer::disable)
		.sessionManagement(
			sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
	web.ignoring().mvcMatchers("/authentication");
    }

    @Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
	corsConfig.setAllowCredentials(true);
	corsConfig.addAllowedHeader("*");
	corsConfig.addAllowedMethod("*");
	corsConfig.addAllowedOrigin("*");
	source.registerCorsConfiguration("/**", corsConfig);
        return new CorsFilter(source);
    }

    @Bean
    @Primary
    ObjectMapper objectMapperConfig(Jackson2ObjectMapperBuilder builder) {
        return builder.createXmlMapper(false)
		.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .modules(new Jdk8Module(), new JavaTimeModule())
                .build().configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }

}
