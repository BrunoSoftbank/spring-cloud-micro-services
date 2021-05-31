package br.com.softbank.email.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.*;

import br.com.softbank.email.properties.DeadLetterProperties;
import br.com.softbank.email.properties.ExchangeProperties;
import br.com.softbank.email.properties.ParkingLotProperties;
import br.com.softbank.email.properties.RouttingKeyProperties;

@Configuration
public class RabbitEmailConsumerConfig {

	@Autowired
	private ExchangeProperties exchangeProperties;
	@Autowired
	private RouttingKeyProperties routtingKeyProperties;
	@Autowired
	private DeadLetterProperties deadLetterProperties;
	@Autowired
	private ParkingLotProperties parkingLotProperties;

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchangeProperties.getEmailExchenge());
	}

	@Bean
	public Queue routtingKey() {
		return QueueBuilder.durable(routtingKeyProperties.getEmailRoutingKey()).deadLetterExchange(exchangeProperties.getEmailExchenge()).deadLetterRoutingKey(deadLetterProperties.getEmailDeadLetter()).build();
	}

	@Bean
	public Queue deadLetter() {
		return QueueBuilder.durable(deadLetterProperties.getEmailDeadLetter()).deadLetterExchange(exchangeProperties.getEmailExchenge()).deadLetterRoutingKey(routtingKeyProperties.getEmailRoutingKey()).build();
	}

	@Bean
	public Queue parkingLot() {
		return new Queue(parkingLotProperties.getEmailParkingLot());
	}

	@Bean
	public Binding bindingRouttingKey() {
		return BindingBuilder.bind(routtingKey()).to(exchange()).with(routtingKeyProperties.getEmailRoutingKey());
	}

	@Bean
	public Binding bindingDeadLetter() {
		return BindingBuilder.bind(deadLetter()).to(exchange()).with(deadLetterProperties.getEmailDeadLetter());
	}

	@Bean
	public Binding bindingParkingLot() {
		return BindingBuilder.bind(parkingLot()).to(exchange()).with(parkingLotProperties.getEmailParkingLot());
	}
}
