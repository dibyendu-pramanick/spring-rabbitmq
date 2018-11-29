package com.qcom.rabbitmq.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

	@Autowired
	private  RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String message) {
		rabbitTemplate.convertAndSend("spring-boot-exchange","foo.bar.baz",message);
	}
}
