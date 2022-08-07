package com.demo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

@Service
public class RetryPictureConsumer {

	public static final Logger log = LoggerFactory.getLogger(RetryPictureConsumer.class);
	
	private static final String DEAD_EXCHANGE_NAME = "x.guideline.dead";

	private DlxProcessingErrorHandler dlxProcessingErrorHandler;

	@Autowired
	private ObjectMapper objectMapper;

	public RetryPictureConsumer() {
		this.dlxProcessingErrorHandler = new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
	}

	@RabbitListener(queues = "q.guideline.image.work")
	public void listen(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag)
			throws InterruptedException, JsonParseException, JsonMappingException, IOException {
		try {
			var picture = objectMapper.readValue(message.getBody(), Picture.class);
			
			// process the image
			if (picture.getSize() > 9000) {
				// throw exception, we will use DLX handler for retry mechanism
				throw new IOException("Size too large");
			} else {
				log.info("Creating thumbnail & publishing : " + picture);
				
				// you must acknowledge that message already processed
				channel.basicAck(deliveryTag, false);
			}
		} catch (IOException e) {
			log.warn("Error processing message : " + new String(message.getBody()) + " : " + e.getMessage());
			dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel, deliveryTag);
		}
	}
}
