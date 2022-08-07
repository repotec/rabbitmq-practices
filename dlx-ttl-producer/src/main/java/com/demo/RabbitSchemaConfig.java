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
		return new Declarables(new FanoutExchange("x.picture4"),
							   new FanoutExchange("x.picture4.dlx.ttl"),
							   new Queue("q.picture4", true, false, false, Map.of("x-dead-letter-exchange", "x.picture4.dlx.ttl", 
									   											  "x-message-ttl", 10000)),
							   new Queue("q.picture4.dlx.ttl"),
							   new Binding("q.picture4", DestinationType.QUEUE, "x.picture4", "", null),
							   new Binding("q.picture4.dlx.ttl", DestinationType.QUEUE, "x.picture4.dlx.ttl", "", null));
	}
}


//public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments)