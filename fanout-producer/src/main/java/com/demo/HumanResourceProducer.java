package com.demo;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  to send message to all queues that bound to specific exchange
 */
@Service
public class HumanResourceProducer {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void sendMessage(Employee data) throws IOException {
		var employee = objectMapper.writeValueAsString(data);
		rabbitTemplate.convertAndSend("x.hr", "", employee);
	}
}
