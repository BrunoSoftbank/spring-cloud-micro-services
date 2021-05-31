package br.com.softbank.email.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.rabbitmq.routing-key")
public class RouttingKeyProperties {

	private String emailRoutingKey;

	public String getEmailRoutingKey() {
		return emailRoutingKey;
	}

	public void setEmailRoutingKey(String emailRoutingKey) {
		this.emailRoutingKey = emailRoutingKey;
	}
}
