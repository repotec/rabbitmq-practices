package com.demo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PrefetchConsumer {

	private static final Logger logger = LoggerFactory.getLogger(PrefetchConsumer.class);
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	@RabbitListener(queues = "q.prefetch")
	public void listener(String message) throws IOException, InterruptedException  {
		var employee = objectMapper.readValue(message, Employee.class);
		logger.info("Employee is:" + employee);
		TimeUnit.SECONDS.sleep(10);
	}
}
