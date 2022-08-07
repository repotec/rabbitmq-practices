package com.demo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {

	public static final Logger log = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Employee employee) throws IOException {
		rabbitTemplate.convertAndSend("x.spring.retry.work", String.valueOf(employee.getType()), employee);
	}
}
