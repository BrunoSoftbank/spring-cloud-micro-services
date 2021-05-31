package br.com.softbank.email.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.rabbitmq.dead-letter")
public class DeadLetterProperties {
	
	private String emailDeadLetter;

	public String getEmailDeadLetter() {
		return emailDeadLetter;
	}

	public void setEmailDeadLetter(String emailDeadLetter) {
		this.emailDeadLetter = emailDeadLetter;
	}
}
