package com.demo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImageConsumer {
	private static final Logger logger = LoggerFactory.getLogger(ImageConsumer.class);

	@Autowired
	ObjectMapper mapper;
	
	@RabbitListener(queues = "q.picture3")
	public void listener(String message) throws IOException{
		var picture = mapper.readValue(message, Picture.class);
		
		if(picture.getSize() > 9000) {
			throw new AmqpRejectAndDontRequeueException("picture size is too large");
		}
		logger.info("picture {}", picture);
	}
}
