package com.demo;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  to send message to all queues that bound to specific exchange
 */
@Service
public class MultiplePrefetchProducer {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void sendTransMessage() throws IOException {
		for (int i = 0; i < 20000; i++) {
			Dummy data = new Dummy(i, LocalDateTime.now());
			rabbitTemplate.convertAndSend("x.prefetch.trans", "", objectMapper.writeValueAsString(data));
		}
	}
	
	public void sendAccsMessage() throws IOException {
		for (int i = 0; i < 200; i++) {
			Dummy data = new Dummy(i, LocalDateTime.now());
			rabbitTemplate.convertAndSend("x.prefetch.accs", "", objectMapper.writeValueAsString(data));
		}
	}
}
