package br.com.softbank.email.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RabbitEmailRepublishIntegration {

	private static final String X_RETRIES_HEADER = "x-retries";

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${LIMIT_RETRY}")
	private String limit_retry;

	@Value("${spring.rabbitmq.exchenge.emailExchenge}")
	private String exchange;
	@Value("${spring.rabbitmq.dead-letter.emailDeadLetter}")
	private String deadLetter;
	@Value("${spring.rabbitmq.routing-key.emailRoutingKey}")
	private String routingKey;
	@Value("${spring.rabbitmq.parking-lot.emailParkingLot}")
	private String parkingLot;

	@Scheduled(cron = "${CRON_TAB_RETRY_DEAD_LETTER}")
	public void rePublish() {
		List<Message> messages = getAllMessagesFromDeadLetter();

		messages.forEach(message -> {
			Map<String, Object> headers = message.getMessageProperties().getHeaders();
			Integer retriesHeader = (Integer) headers.get(X_RETRIES_HEADER);

			if (retriesHeader == null) {
				retriesHeader = 0;
			}

			if (retriesHeader < Integer.valueOf(limit_retry)) {
				headers.put(X_RETRIES_HEADER, retriesHeader + 1);
				rabbitTemplate.convertAndSend(routingKey, message);
			} else {
				rabbitTemplate.convertAndSend(parkingLot, message);
			}
		});
	}

	private List<Message> getAllMessagesFromDeadLetter() {
		List<Message> messages = new ArrayList<Message>();
		boolean isNull = true;
		Message message = null;

		do {
			message = rabbitTemplate.receive(deadLetter);
			if (message != null) {
				messages.add(message);
			} else {
				isNull = false;
			}

		} while (isNull);

		return messages;
	}

}
