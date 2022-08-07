package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class HumanResourceConsumer {
	public static final Logger logger = LoggerFactory.getLogger(HumanResourceConsumer.class);
	
	@RabbitListener(queues = "q.hr.register")
	@SendTo("x.hr.register.refused/")
	public Employee employeeRegisterListener(Employee employee) {
		if (employee.getSalary() > 9000) {
			logger.info("employee has been sent to refused queue");
			return employee;
		}
		
		logger.info("employee has been proccessed");
		
		return null;
	}
}
