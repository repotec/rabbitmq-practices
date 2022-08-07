package com.demo;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Object message, String key) throws IOException {
		rabbitTemplate.convertAndSend("x.home", key, message);
	}
}
