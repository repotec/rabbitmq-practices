package com.demo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

/**
 * manually transfer to exchange that setup as dead letter exchange to 'q.picture.ack' queue 
 * transfer will happen if image is large than 9000 KB through the channel
 *
 */
@Service
public class ImageConsumer {
	private static final Logger logger = LoggerFactory.getLogger(ImageConsumer.class);

	@Autowired
	ObjectMapper mapper;
	
	@RabbitListener(queues = "q.picture.ack")
	public void listener(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException{
		var picture = mapper.readValue(message, Picture.class);
		
		if(picture.getSize() > 9000) {
			channel.basicReject(tag, false);
			return;
		}
		logger.info("tag {} | picture {} ", tag, picture);
		
		channel.basicAck(tag, false);
	}
}
