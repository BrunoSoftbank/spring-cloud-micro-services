package br.com.softbank.email.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.softbank.email.dto.UsuarioEmailDTO;
import br.com.softbank.email.properties.DeadLetterProperties;
import br.com.softbank.email.service.EmailService;

@Component
public class RabbitEmailConsumerIntegration {
	
	private static final Logger LOG = LoggerFactory.getLogger(RabbitEmailConsumerIntegration.class);
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private DeadLetterProperties deadLetterProperties;
	
	@RabbitListener(queues = {"${spring.rabbitmq.routing-key.emailRoutingKey}"})
    public void receive(UsuarioEmailDTO usuarioEmailDTO) {		
		LOG.warn(this.getClass().getSimpleName() + ".receive(String json) " + usuarioEmailDTO);
        		
		// APENAS PARA SIMULAR QUE O SMTP DA GMAIL ESTA FORA, E ENVIAR PARA A DEAD LETTER
		if(usuarioEmailDTO.getEmail().contains("@yahoo.com.br")) {
			throw new AmqpRejectAndDontRequeueException("Enviando para a Dead Letter: " + deadLetterProperties.getEmailDeadLetter());
		}
		
		emailService.emailWellcome(usuarioEmailDTO);
    }

}
