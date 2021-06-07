package br.com.softbank.email.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("spring.rabbitmq.dead-letter")
public class DeadLetterProperties {
	
	private String emailDeadLetter;
}
