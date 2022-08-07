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
public class MultiplePrefetchConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(MultiplePrefetchConsumer.class);
	
	@Autowired
	ObjectMapper objectMapper;
	
	@RabbitListener(queues = "q.prefetch.trans", concurrency = "2")
	public void transMessageListener(String message) throws IOException {
		Dummy data = objectMapper.readValue(message, Dummy.class);
		log.info(data.toString());
	}
	
	@RabbitListener(queues = "q.prefetch.accs", concurrency = "2", containerFactory = "customContainerFactory")
	public void accsMessageListener(String message) throws IOException, InterruptedException {
		Dummy data = objectMapper.readValue(message, Dummy.class);
		log.info(data.toString());
		TimeUnit.MINUTES.sleep(1);
	}
}
