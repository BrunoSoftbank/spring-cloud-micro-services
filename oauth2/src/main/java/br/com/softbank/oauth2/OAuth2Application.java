package br.com.softbank.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import br.com.softbank.oauth2.service.CustomUserDetailsService;

@SpringBootApplication
@EnableEurekaClient
@EnableAuthorizationServer
@EnableResourceServer
@EnableWebSecurity
public class OAuth2Application implements CommandLineRunner {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	public static void main(String[] args) {
		SpringApplication.run(OAuth2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customUserDetailsService.createUserAdminIfNotExists();	
	}
}
