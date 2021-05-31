package br.com.softbank.consultawebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConsultaWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaWebServiceApplication.class, args);
	}
}
