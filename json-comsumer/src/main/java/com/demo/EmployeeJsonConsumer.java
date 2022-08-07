package com.demo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeJsonConsumer {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	ObjectMapper mapper;
	
	
	@RabbitListener(queues = "q.employees")
	public void listener(String message) throws IOException {
		var employee = mapper.readValue(message, Employee.class);
		
		logger.info("employee is {}", employee);
	}
}
