package com.demo;

import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitSchemaConfig {
	
	@Bean
	public Declarables rabbitSchema() {
		return new Declarables(new FanoutExchange("x.picture3"),
							   new FanoutExchange("x.picture3.dlx"),
							   new Queue("q.picture3", true, false, false, Map.of("x-dead-letter-exchange", "x.picture3.dlx")),
							   new Queue("q.picture3.dlx"),
							   new Binding("q.picture3", DestinationType.QUEUE, "x.picture3", "", null),
							   new Binding("q.picture3.dlx", DestinationType.QUEUE, "x.picture3.dlx", "", null));
	}
}