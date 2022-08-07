package com.demo;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeJsonProducer {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	ObjectMapper mapper;
	
	public void sendEmployee(Employee employee) throws IOException {
		var json = mapper.writeValueAsString(employee);
		rabbitTemplate.convertAndSend("q.employees", json);
	}
}
