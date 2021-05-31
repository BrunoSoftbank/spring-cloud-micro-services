package br.com.softbank.exame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
@EnableResourceServer
public class ExameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExameApplication.class, args);
	}
}
