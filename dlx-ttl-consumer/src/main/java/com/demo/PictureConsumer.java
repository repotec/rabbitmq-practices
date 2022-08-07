package com.demo;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PictureConsumer {
	
	@Autowired
	ObjectMapper mapper;
	
	@RabbitListener(queues = "q.picture4.ttl")
	public void listener(String message) throws IOException {
		var json = mapper.readValue(message, Picture.class);
		
	}
}
