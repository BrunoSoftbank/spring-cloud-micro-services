package br.com.softbank.usuario.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/v1/usuarios/**").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/v1/usuarios/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/v1/usuarios/**").permitAll()
		.antMatchers("/swagger-ui.html#").permitAll();
	}
}
