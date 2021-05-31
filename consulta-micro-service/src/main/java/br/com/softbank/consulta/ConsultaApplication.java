package br.com.softbank.consulta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableResourceServer
public class ConsultaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaApplication.class, args);
	}
}
