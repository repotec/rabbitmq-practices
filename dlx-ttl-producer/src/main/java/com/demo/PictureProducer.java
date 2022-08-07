package com.demo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PictureProducer {

	public static final Logger log = LoggerFactory.getLogger(PictureProducer.class);
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Picture picture) throws IOException {
		var json = mapper.writeValueAsString(picture);
		rabbitTemplate.convertAndSend("x.picture4", "", json);
	}
}
