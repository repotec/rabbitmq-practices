package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@RabbitListener(queues = "q.spring.retry.work")
	public void listener(Employee employee) throws IllegalAccessException {
		logger.info("processing employee message " + employee);
		
		if(employee.getSalary() > 9000) {
			throw new IllegalAccessException("salary is too large");
		}
	}
}
