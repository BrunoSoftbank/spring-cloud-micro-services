package br.com.softbank.email.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.rabbitmq.parking-lot")
public class ParkingLotProperties {
	
	private String emailParkingLot;

	public String getEmailParkingLot() {
		return emailParkingLot;
	}

	public void setEmailParkingLot(String emailParkingLot) {
		this.emailParkingLot = emailParkingLot;
	}
}
