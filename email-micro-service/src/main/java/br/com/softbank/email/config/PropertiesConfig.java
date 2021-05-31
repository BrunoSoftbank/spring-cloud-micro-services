package br.com.softbank.email.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import br.com.softbank.email.properties.DeadLetterProperties;
import br.com.softbank.email.properties.ExchangeProperties;
import br.com.softbank.email.properties.ParkingLotProperties;
import br.com.softbank.email.properties.RabbitProperties;
import br.com.softbank.email.properties.RouttingKeyProperties;

@Configuration
@EnableConfigurationProperties({RabbitProperties.class, ExchangeProperties.class, RouttingKeyProperties.class, DeadLetterProperties.class, ParkingLotProperties.class})
public class PropertiesConfig {

}
