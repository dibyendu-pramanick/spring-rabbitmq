package com.qcom.rabbitmq.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qcom.rabbitmq.config.MessagePublisher;

@RestController
@RequestMapping("/api")
public class PublishAPI {
	
	@Autowired
	public MessagePublisher messagePublisher;
	
	@GetMapping("/publish")
	public String publish() {
		messagePublisher.sendMessage("This is first message");
		return "{'success':'true'}";
	}

}
