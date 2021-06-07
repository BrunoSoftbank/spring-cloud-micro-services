package br.com.softbank.usuario.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("spring.rabbitmq")
public class RabbitProperties {
	
	private String host;
	private String port;
	private String username;
	private String password;
}
