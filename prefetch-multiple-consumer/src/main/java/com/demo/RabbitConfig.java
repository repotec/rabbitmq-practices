package com.demo;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitConfig {
	
	@Bean
	public ObjectMapper objectMapper() {
		return JsonMapper.builder().findAndAddModules().build();
	}
	
	@Bean
	public RabbitListenerContainerFactory<SimpleMessageListenerContainer> customContainerFactory
	(SimpleRabbitListenerContainerFactoryConfigurer config, ConnectionFactory connection){
		SimpleRabbitListenerContainerFactory customFactory = new SimpleRabbitListenerContainerFactory();
		config.configure(customFactory, connection);
		customFactory.setPrefetchCount(1);
		
		return customFactory;
	}
}
