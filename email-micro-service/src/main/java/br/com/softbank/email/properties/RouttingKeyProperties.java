package br.com.softbank.email.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("spring.rabbitmq.routing-key")
public class RouttingKeyProperties {

	private String emailRoutingKey;
}
