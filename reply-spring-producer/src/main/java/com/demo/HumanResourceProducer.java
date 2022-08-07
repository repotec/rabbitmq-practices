package com.demo;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanResourceProducer {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Employee employee) throws IOException {
		rabbitTemplate.convertAndSend("x.hr.register", "", employee);
	}
}
