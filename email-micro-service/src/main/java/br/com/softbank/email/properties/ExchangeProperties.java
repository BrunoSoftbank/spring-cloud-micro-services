package br.com.softbank.email.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.rabbitmq.exchenge")
public class ExchangeProperties {

	private String emailExchenge;

	public String getEmailExchenge() {
		return emailExchenge;
	}

	public void setEmailExchenge(String emailExchenge) {
		this.emailExchenge = emailExchenge;
	}
}
