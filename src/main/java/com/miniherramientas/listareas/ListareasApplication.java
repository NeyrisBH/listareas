package com.miniherramientas.listareas;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@SpringBootApplication
public class ListareasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListareasApplication.class, args);
	}
	
	@EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "HEAD"));
            configuration.setExposedHeaders(Arrays.asList("Authorization"));
            configuration.setAllowedOrigins(Arrays.asList("*"));

            http.addFilterAfter(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/token").permitAll()
            .antMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
            .antMatchers(HttpMethod.GET, "/api/personas").hasAuthority("SCOPED_Admin")
            .antMatchers(HttpMethod.GET, "/api/personas/**").hasAuthority("SCOPED_User")
            .anyRequest().authenticated()
            .and().csrf().disable().cors().configurationSource(request -> configuration);
            
        }
    }
}
