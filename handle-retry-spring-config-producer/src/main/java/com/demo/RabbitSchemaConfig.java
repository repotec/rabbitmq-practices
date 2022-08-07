package com.demo;

import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * we do not need wait queue here
 *
 */
@Configuration
public class RabbitSchemaConfig {
	
	@Bean
	public Declarables rabbitSchema() {
		return new Declarables(new DirectExchange("x.spring.retry.work"),
							   new DirectExchange("x.spring.retry.dead"),
							   
							   new Queue("q.spring.retry.work", true, false, false, Map.of("x-dead-letter-exchange", "x.spring.retry.dead")),
							   new Queue("q.spring.retry.dead", true, false, false, null),
							   
							   new Binding("q.spring.retry.work", DestinationType.QUEUE, "x.spring.retry.work", "employee", null),
							   new Binding("q.spring.retry.work", DestinationType.QUEUE, "x.spring.retry.work", "manager", null),
							   
							   new Binding("q.spring.retry.dead", DestinationType.QUEUE, "x.spring.retry.dead", "employee", null),
							   new Binding("q.spring.retry.dead", DestinationType.QUEUE, "x.spring.retry.dead", "manager", null));
	}
}