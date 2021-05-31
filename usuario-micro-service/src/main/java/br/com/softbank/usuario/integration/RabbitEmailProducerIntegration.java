package br.com.softbank.usuario.integration;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.softbank.usuario.dto.UsuarioEmailDTO;

@Component
public class RabbitEmailProducerIntegration {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendToQueue(UsuarioEmailDTO usuarioEmailDTO) {
		rabbitTemplate.convertAndSend("routing-key-email-welccome", usuarioEmailDTO);	
	}
}
